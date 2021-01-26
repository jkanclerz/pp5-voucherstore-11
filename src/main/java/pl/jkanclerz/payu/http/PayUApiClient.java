package pl.jkanclerz.payu.http;

import pl.jkanclerz.payu.exceptions.PayUException;

import java.net.http.HttpResponse;
import java.util.Map;

public interface PayUApiClient {
    HttpResponse<String> post(String url, String body, Map<String, String> headers) throws PayUException;
}
