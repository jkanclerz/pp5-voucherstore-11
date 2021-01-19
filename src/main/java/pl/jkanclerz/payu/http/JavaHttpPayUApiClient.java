package pl.jkanclerz.payu.http;

import pl.jkanclerz.payu.exceptions.PayUException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Map;

public class JavaHttpPayUApiClient implements PayUApiClient {
    private final HttpClient httpClient;

    public JavaHttpPayUApiClient() {
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(20))
                .build();
    }

    @Override
    public HttpResponse<String> post(String url, String body, Map<String, String> headers) throws PayUException {
        var httpRequestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.ofString(body));
        headers.forEach(httpRequestBuilder::header);

        try {
            HttpResponse<String> r = httpClient.send(httpRequestBuilder.build(),HttpResponse.BodyHandlers.ofString());

            return r;
        } catch (IOException | InterruptedException e) {
            throw new PayUException(e);
        }
    }
}
