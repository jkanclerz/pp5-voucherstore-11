package pl.jkanclerz.payu;

import lombok.Getter;

@Getter
public class PayUCredentials {

    private final String posId;
    private final String secondKey;
    private final String clientId;
    private final String clientSecret;
    private final String baseUrl;
    private final String notifyUrl;

    public PayUCredentials(String posId, String secondKey, String clientId, String clientSecret, String baseUrl) {
        this.posId = posId;
        this.secondKey = secondKey;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.baseUrl = baseUrl;
        this.notifyUrl = "http://127.0.0.1/notify/me";
    }

    public static PayUCredentials sandbox() {
        return new PayUCredentials(
                "300746",
                "b6ca15b0d1020e8094d9b5f8d163db54",
                "300746",
                "2ee86a66e5d97e3fadc400c9f19b065d",
                "https://secure.snd.payu.com");
    }

    public static PayUCredentials production(String posId, String secondKey, String clientId, String clientSecret) {
        return new PayUCredentials(
                posId,
                secondKey,
                clientId,
                clientSecret,
                "https://secure.payu.com");
    }

    public static PayUCredentials productionOfEnv() {
        return new PayUCredentials(
                System.getenv("PAYU_POS_ID"),
                System.getenv("PAYU_SECOND_KEY"),
                System.getenv("PAYU_CLIENT_ID"),
                System.getenv("PAYU_CLIENT_SECRET"),
                "https://secure.payu.com");
    }
}
