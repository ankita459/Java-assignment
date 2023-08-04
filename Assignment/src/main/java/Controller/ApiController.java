package Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Entity.UserCredentials;
import Service.AuthService;

@RestController

public class ApiController {
	
	private final AuthService authService;

    public ApiController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/authenticate")
    public String authenticate(@RequestBody UserCredentials userCredentials) {
        String token = null;
		try {
			token = AuthService.authenticateUser(userCredentials.getLogin_id(), userCredentials.getPassword());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if (token != null) {
            return "Bearer " + token;
        }
        return "Authentication failed";
    }

}
