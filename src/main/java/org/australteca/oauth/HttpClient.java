package org.australteca.oauth;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Created by tomi on 13/07/17.
 */
public class HttpClient{

    public int sendPost(String url, List<HttpParameterPair> parameters) throws IOException {

        org.apache.commons.httpclient.HttpClient client = new org.apache.commons.httpclient.HttpClient();
        PostMethod method = new PostMethod(url);
        for(HttpParameterPair p: parameters){
            method.addParameter(p.param, p.value);
        }

        int statusCode = 0;
        try {
            statusCode = client.executeMethod(method);
            byte[] responseBody = method.getResponseBody();
            System.out.println(new String(responseBody));
        } catch (Exception e) {
            System.err.println("Fatal error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            method.releaseConnection();
        }

        return statusCode;
    }
}
