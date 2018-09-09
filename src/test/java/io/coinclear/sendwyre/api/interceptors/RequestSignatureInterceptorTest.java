package io.coinclear.sendwyre.api.interceptors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;


public class RequestSignatureInterceptorTest {

    private RequestSignatureInterceptor interceptor;
    private String urlToTest;
    private String secretKey;

    @Before
    public void setUp() throws Exception {
        secretKey = "SK-X6ABP7DG-2N92PBW3-XNQPM46V-3LLWGARL";
        urlToTest = "https://api.testwyre.com/account?timestamp=1536518677854";
        interceptor = RequestSignatureInterceptor.builder()
                .headerValue("X-Api-Signature")
                .secretKey(secretKey)
                .build();
    }

    @Test
    public void testComputedSignatures() {
        String s = interceptor.computeSignature(urlToTest, "");
        String s1 = computeSignature(secretKey, urlToTest, "");

        System.out.println(s1);
        Assert.assertEquals("Computed Signatures should match", s, s1);
    }

    private static String computeSignature(String secretKey, String url, String reqData) {

        String data = url + reqData;

        try {
            Mac sha256Hmac = Mac.getInstance("HmacSHA256");
            SecretKeySpec key = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
            sha256Hmac.init(key);

            byte[] macData = sha256Hmac.doFinal(data.getBytes(StandardCharsets.UTF_8));

            StringBuilder result = new StringBuilder();
            for (final byte element : macData) {
                result.append(Integer.toString((element & 0xff) + 0x100, 16).substring(1));
            }
            return result.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}