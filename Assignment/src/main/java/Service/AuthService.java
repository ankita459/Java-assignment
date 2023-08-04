package Service;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import Entity.BearerToken;
import Entity.UserCredentials;

@Service
public class AuthService {
	
	private static WebClient webClient;

    public AuthService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://qa2.sunbasedata.com/sunbase/portal/api").build();
    }

    public static String authenticateUser(String loginId, String password) {
        UserCredentials userCredentials = new UserCredentials(loginId, password);
        BearerToken bearerToken = webClient.post()
                .uri("/assignment_auth.jsp")
                .body(BodyInserters.fromValue(userCredentials))
                .retrieve()
                .bodyToMono(BearerToken.class)
                .block();

        if (bearerToken != null) {
            return bearerToken.getToken();
        }
        return null;
    }

}
