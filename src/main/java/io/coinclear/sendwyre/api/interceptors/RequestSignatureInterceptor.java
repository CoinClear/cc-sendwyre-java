package io.coinclear.sendwyre.api.interceptors;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Builder;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Slf4j
@Builder
@NoArgsConstructor
@AllArgsConstructor(staticName = "newInstance")
public class RequestSignatureInterceptor implements Interceptor {

    private static final String HMAC = "HmacSHA256";

    @NonNull
    private String headerValue;
    @NonNull
    private String secretKey;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        String url = originalRequest.url().toString();
        url += (((url.indexOf("?") > 0) ? "&" : "?") + "timestamp=" + System.currentTimeMillis());

        String data = StringUtils.EMPTY;
        if (Objects.nonNull(originalRequest.body())) {
            data = originalRequest.body().toString();
        }
        String requestSignature = computeSignature(url, data);
        Request.Builder builder = originalRequest.newBuilder()
                .url(url)
                .method(originalRequest.method(), originalRequest.body())
                .header(headerValue, requestSignature);
        Request newRequest = builder.build();

        return chain.proceed(newRequest);
    }

    public String computeSignature(String url, String reqData) {
        String data = url + reqData;

        try {
            Mac sha256Hmac = Mac.getInstance(HMAC);
            SecretKeySpec key = new SecretKeySpec(secretKey.getBytes(), HMAC);
            sha256Hmac.init(key);

            byte[] macData = sha256Hmac.doFinal(data.getBytes(StandardCharsets.UTF_8));

            StringBuilder result = new StringBuilder();
            for (final byte element : macData) {
                result.append(Integer.toString((element & 0xff) + 0x100, 16).substring(1));
            }
            return result.toString();
        } catch (Exception e) {
            log.error("Unable to create request signature: ", e);
            return StringUtils.EMPTY;
        }
    }
}
