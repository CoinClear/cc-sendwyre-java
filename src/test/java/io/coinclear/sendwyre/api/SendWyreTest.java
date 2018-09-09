package io.coinclear.sendwyre.api;

import io.coinclear.sendwyre.api.dto.AccountDetails;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import retrofit2.Response;


@RunWith(MockitoJUnitRunner.class)
public class SendWyreTest {

    private SendWyre sendWyre;

    @Before
    public void setUp() throws Exception {
        String apiKey = "AK-RTB86CZB-R6M3Y4XC-3JDWPCV7-LZ9896QM";
        String secretKey = "SK-X6ABP7DG-2N92PBW3-XNQPM46V-3LLWGARL";
        this.sendWyre = new SendWyreClientBuilder()
                .testBaseUrl()
                .setApiKey(apiKey)
                .setSecretKey(secretKey)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testClientBuilderApiKeys() {

    }

    @Test(expected = IllegalArgumentException.class)
    public void testClientBuilderBaseUrl() {

    }

    @Test
    public void testGetAccountDetails() throws Exception {
        Response<AccountDetails> detailsResponse = sendWyre.getAccountDetails().execute();
        System.out.println(detailsResponse);
    }


}
