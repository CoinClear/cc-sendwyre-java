package io.coinclear.sendwyre.api;

import io.coinclear.sendwyre.api.dto.AccountDetails;
import okhttp3.logging.HttpLoggingInterceptor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import retrofit2.Call;
import retrofit2.Response;


@RunWith(MockitoJUnitRunner.class)
public class SendWyreTest {
    private static String apiKey = "AK-RTB86CZB-R6M3Y4XC-3JDWPCV7-LZ9896QM";
    private static String secretKey = "SK-X6ABP7DG-2N92PBW3-XNQPM46V-3LLWGARL";

    private SendWyre sendWyre;

    @Before
    public void setUp() throws Exception {
        this.sendWyre = new SendWyreClientBuilder()
                .testBaseUrl()
                .setApiKey(apiKey)
                .setSecretKey(secretKey)
                .logLevel(HttpLoggingInterceptor.Level.HEADERS)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testClientBuilderApiKey() {
        this.sendWyre = new SendWyreClientBuilder()
                .testBaseUrl()
                .setSecretKey(secretKey)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testClientBuilderSecretKey() {
        this.sendWyre = new SendWyreClientBuilder()
                .testBaseUrl()
                .setApiKey(apiKey)
                .build();
    }


    @Test(expected = IllegalArgumentException.class)
    public void testClientBuilderBaseUrl() {
        this.sendWyre = new SendWyreClientBuilder()
                .setApiKey(apiKey)
                .setSecretKey(secretKey)
                .build();
    }

    @Test
    public void testGetAccountDetails() throws Exception {
        Call<AccountDetails> detailsCall = sendWyre.getAccountDetails();
        Response<AccountDetails> response = detailsCall.execute();
        Assert.assertTrue("Expected 200 status code", response.isSuccessful());
    }
}
