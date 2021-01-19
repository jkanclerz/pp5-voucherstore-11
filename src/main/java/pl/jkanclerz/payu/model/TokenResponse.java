package pl.jkanclerz.payu.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponse {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("token_type")
    private String tokenType;
    @JsonProperty("expires_in")
    private Integer expiresIn;
    @JsonProperty("grant_type")
    private String grantType;
    /*
    {
        "access_token":"8f79c971-195e-43f5-bd83-ad2104414acc",
        "token_type":"bearer",
        "expires_in":43199, //czas ważności w sekundach
        "grant_type":"client_credentials"
}
     */
}
