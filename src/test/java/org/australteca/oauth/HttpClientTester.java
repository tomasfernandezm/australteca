package org.australteca.oauth;

import org.australteca.Constants;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by tomi on 13/07/17.
 */
public class HttpClientTester {

    @Test
    public void sendPostTest(){

        HttpClient httpClient = new HttpClient();

        String url = "https://localhost:8080/j_security_check";

        List<HttpParameterPair> parameters = new ArrayList<>();
        parameters.add(new HttpParameterPair(Constants.LOGIN_USERNAME_FIELD, "tomas.martinez@ing.austral.edu.ar"));
        parameters.add(new HttpParameterPair(Constants.LOGIN_PASSWORD_FIELD, "123456789"));
        int responsecode = 0;
        try {
            responsecode = httpClient.sendPost(url, parameters);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertThat(responsecode).isEqualTo(200);
    }
}
