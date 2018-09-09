package io.coinclear.sendwyre.api;

import io.coinclear.sendwyre.api.interceptors.ApiKeyInterceptor;
import io.coinclear.sendwyre.api.interceptors.LoggingInterceptor;
import io.coinclear.sendwyre.api.interceptors.RequestSignatureInterceptor;
import lombok.NoArgsConstructor;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.TlsVersion;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.KeyStore;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * Builder for creating a {@link SendWyre} client
 */
@NoArgsConstructor
public final class SendWyreClientBuilder {

    private static final String X_API_KEY = "X-Api-Key";
    private static final String X_API_SIGNATURE = "X-Api-Signature";

    private static final String DEFAULT_PRODUCTION_BASE_URL = "https://api.sendwyre.com";
    private static final String DEFUALT_TEST_BASE_URL = "https://api.testwyre.com";
    private static final long DEFAULT_READ_TIMEOUT_SECONDS = 300;
    private static final long DEFAULT_CONNECT_TIMEOUT_SECONDS = 5;

    // a more restrictive connection spec based on the MODERN_TLS spec already present in OkHttp
    private static final ConnectionSpec CONNECTION_SPEC =
            new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                    .tlsVersions(TlsVersion.TLS_1_2)
                    .build();

    // SendWyre client fields
    private String apiKey;
    private String secretKey;
    private String baseUrl;

    // OkHttp Client Configurations
    private OkHttpClient.Builder okHttpClientBuilder;
    private HttpLoggingInterceptor.Level httpLogLevel;
    private long readTimeoutSeconds;
    private long connectTimeoutSeconds;

    /**
     * Builds a client implementation of the SendWyre API
     *
     * @return {@link SendWyre}
     * @throws IllegalArgumentException if the required fields are missing
     */
    public SendWyre build() {
        if (baseUrl == null) {
            throw new IllegalArgumentException(
                    "must set baseUrl. You probably want to call productionBaseUrl() or testBaseUrl()");
        }

        if (apiKey == null || secretKey == null) {
            throw new IllegalArgumentException("must set api and secret keys!");
        }

        this.okHttpClientBuilder = new OkHttpClient.Builder();
        this.readTimeoutSeconds = DEFAULT_READ_TIMEOUT_SECONDS;
        this.connectTimeoutSeconds = DEFAULT_CONNECT_TIMEOUT_SECONDS;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(this.baseUrl)
                .validateEagerly(true)
                .client(buildOkHttpClient())
                .addConverterFactory(JacksonConverterFactory.create(ObjectMapperProvider.getDefaultMapper()))
                .build();

        return retrofit.create(SendWyre.class);
    }

    private OkHttpClient buildOkHttpClient() {
        okHttpClientBuilder
                .readTimeout(readTimeoutSeconds, TimeUnit.SECONDS)
                .connectTimeout(connectTimeoutSeconds, TimeUnit.SECONDS)
                .followSslRedirects(false)
                .connectionSpecs(Collections.singletonList(CONNECTION_SPEC));

        if (httpLogLevel != null) {
            okHttpClientBuilder.addInterceptor(new HttpLoggingInterceptor().setLevel(httpLogLevel));
        }
        okHttpClientBuilder.addInterceptor(new LoggingInterceptor());

        ApiKeyInterceptor apiKeyInterceptor = ApiKeyInterceptor.builder()
                .apiKey(this.apiKey)
                .headerValue(X_API_KEY)
                .build();
        okHttpClientBuilder.addInterceptor(apiKeyInterceptor);

        RequestSignatureInterceptor signatureInterceptor = RequestSignatureInterceptor.builder()
                .secretKey(this.secretKey)
                .headerValue(X_API_SIGNATURE)
                .build();
        okHttpClientBuilder.addInterceptor(signatureInterceptor);
        okHttpClientBuilder.addNetworkInterceptor(signatureInterceptor);

        checkRuntimeSupportsTls12(okHttpClientBuilder);

        return okHttpClientBuilder.build();
    }

    /**
     * This SendWyre API requires TLSv1.2, which is well-supported
     * on JDK 8, but spotty on JDK 7 and below.
     * <p>
     * This attempts to detect whether TLSv1.2 is already enabled by default,
     * and if not, enable it, or failing that throw an error early.
     *
     * @param okHttpClientBuilder the OkHttpClient builder
     */
    private void checkRuntimeSupportsTls12(OkHttpClient.Builder okHttpClientBuilder) {
        SSLSocket testSslSocket = null;

        try {
            // create a temporary client and test socket to check for desired cipher and protocol support
            OkHttpClient testOkHttpClient = okHttpClientBuilder.build();
            testSslSocket = (SSLSocket) testOkHttpClient.sslSocketFactory().createSocket();

            // does the test socket work with our connection spec's cipher suite and tls version as-is?
            if (CONNECTION_SPEC.isCompatible(testSslSocket)) {
                return; // no further questions!
            }

            // perhaps TLSv1.2 is supported, just not enabled by default (some versions of Java 7)
            if (!Arrays.asList(testSslSocket.getSupportedProtocols())
                    .contains(TlsVersion.TLS_1_2.javaName())) {
                throw new RuntimeException("This JRE's SSL implementation does not support TLSv1.2. Bailing out.");
            }

            // supported but not enabled by default. In this case, we'll have our OkHTTP
            // client use an SSLSocketFactory which does enable it.

            // The following SSLSocketFactory setup code is from
            // OkHttpClient.Builder#sslSocketFactory()'s javadocs
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init((KeyStore) null);
            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
            if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
                throw new IllegalStateException("Unexpected default trust managers:"
                                                        + Arrays.toString(trustManagers));
            }
            X509TrustManager trustManager = (X509TrustManager) trustManagers[0];

            SSLContext sslContext = SSLContext.getInstance(TlsVersion.TLS_1_2.javaName());
            sslContext.init(null, new TrustManager[]{trustManager}, null);
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            okHttpClientBuilder.sslSocketFactory(sslSocketFactory, trustManager);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            if (testSslSocket != null) {
                try {
                    testSslSocket.close();
                } catch (IOException ex) {
                    // oh well
                }
            }
        }
    }

    /**
     * API Key created in SendWyre dashboard
     * https://dash.sendwyre.com/
     *
     * @param apiKey - API Key Value XXXX-XXXX-XXXX-XXXX
     * @return builder
     */
    public SendWyreClientBuilder setApiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    /**
     * Secret Key created in SendWyre dashboard
     * https://dash.sendwyre.com/
     *
     * @param secretKey - secret Key Value XXXX-XXXX-XXXX-XXXX
     * @return builder
     */
    public SendWyreClientBuilder setSecretKey(String secretKey) {
        this.secretKey = secretKey;
        return this;
    }

    /**
     * Convenience method to set the HTTP client's logging level.
     *
     * @param level Desired logging level.
     * @return builder.
     */
    public SendWyreClientBuilder logLevel(HttpLoggingInterceptor.Level level) {
        this.httpLogLevel = level;
        return this;
    }

    /**
     * The API endpoint (production or test) for SendWyre
     * https://www.sendwyre.com/docs/#production-test-endpoints
     *
     * @param baseUrl - string url
     * @return builder
     */
    public SendWyreClientBuilder setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    /**
     * Configure the client to use the default {@link #DEFAULT_PRODUCTION_BASE_URL production URL}.
     *
     * @return builder
     */
    public SendWyreClientBuilder productionBaseUrl() {
        return setBaseUrl(DEFAULT_PRODUCTION_BASE_URL);
    }

    /**
     * Configure the client to use the default {@link #DEFUALT_TEST_BASE_URL sandbox URL}.
     *
     * @return builder
     */
    public SendWyreClientBuilder testBaseUrl() {
        return setBaseUrl(DEFUALT_TEST_BASE_URL);
    }
}
