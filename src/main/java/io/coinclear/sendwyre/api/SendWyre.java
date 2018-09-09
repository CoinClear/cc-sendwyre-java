package io.coinclear.sendwyre.api;

import io.coinclear.sendwyre.api.dto.AccountDetails;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * API Abstraction for Endpoints on SendWyre https://www.sendwyre.com/docs
 */
public interface SendWyre {

    /**
     * This endpoint retrieves all the information related to your account.
     * All of the information displayed in the Wyre dashboard can be found here.
     * <a>https://www.sendwyre.com/docs/#account-details</a>
     *
     * @return {@link AccountDetails}
     */
    @Headers("X-Api-Version: 2")
    @GET("/account")
    Call<AccountDetails> getAccountDetails();
}