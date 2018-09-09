package io.coinclear.sendwyre.api.interceptors;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Builder;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

@Slf4j
@Builder
@NoArgsConstructor
@AllArgsConstructor(staticName = "newInstance")
public class ApiKeyInterceptor implements Interceptor {

    @NonNull
    private String headerValue;

    @NonNull
    private String apiKey;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();

        Request.Builder builder = originalRequest.newBuilder()
                .header(headerValue, apiKey);

        Request newRequest = builder.build();
        System.out.println(newRequest.headers());
        return chain.proceed(newRequest);
    }
}
