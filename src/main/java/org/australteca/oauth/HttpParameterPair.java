package org.australteca.oauth;

/**
 * Created by tomi on 13/07/17.
 */
public class HttpParameterPair {

    final String param;
    final String value;

    public HttpParameterPair(String param, String value) {
        this.param = param;
        this.value = value;
    }

    public String getParameterString(){
        return param + "=" + value;
    }
}
