package Controller;

import java.awt.desktop.QuitResponse;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import Entity.DeleteResponse;
import Entity.UserCredentials;

@RestController
public class CustomerController {

    @PostMapping("/create-customer")
    public ResponseEntity<String> createCustomer(@RequestBody UserCredentials userCredentials, @RequestHeader("Authorization") String bearerToken) {
        final String apiUrl = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp";

        // Check if the mandatory fields first_name and last_name are present
        if (userCredentials.getFirst_name() == null || userCredentials.getLast_name() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("First Name or Last Name is missing");
        }

        // Create the request body object
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, userCredentials, String.class);

        if (response.getStatusCode() == HttpStatus.CREATED) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Successfully Created");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create customer");
        }
    }
    
    @GetMapping("/get-customer-list")
    public ResponseEntity<UserCredentials[]> getCustomerList(@RequestHeader("Authorization") String bearerToken) {
        final String apiUrl = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=get_customer_list";

        RestTemplate restTemplate = new RestTemplate();
        UserCredentials[] customers = restTemplate.getForObject(apiUrl, UserCredentials[].class);

        if (customers != null) {
            return ResponseEntity.ok(customers);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PostMapping("/delete-customer")
    public ResponseEntity<DeleteResponse> deleteCustomer(
            @RequestParam("uuid") String uuid,
            @RequestHeader("Authorization") String bearerToken) {

        final String apiUrl = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp";

        // Create the request body object with cmd=delete and uuid parameter
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("cmd", "delete");
        requestParams.add("uuid", uuid);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + bearerToken);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestParams, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<DeleteResponse> response = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, DeleteResponse.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return ResponseEntity.ok(new DeleteResponse("Successfully deleted"));
        } else if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DeleteResponse("UUID not found"));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new DeleteResponse("Error: Not deleted"));
        }
    }
    
    @PostMapping("/update-customer")
    public <T> ResponseEntity<T> updateCustomer(
            @RequestParam("uuid") String uuid,
            @RequestHeader("Authorization") String bearerToken,
            @RequestBody UserCredentials userCredentials) {

        final String apiUrl = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp";

        // Check if the request body is empty
        if (userCredentials == null) {
            return ResponseEntity.badRequest().body((T) new Object());
        }

        // Create the request body object with cmd=update, uuid parameter, and customer data
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("cmd", "update");
        requestParams.add("uuid", uuid);
        requestParams.add("first_name", userCredentials.getFirst_name());
        requestParams.add("last_name", userCredentials.getLast_name());
        requestParams.add("street", userCredentials.getStreet());
        requestParams.add("address", userCredentials.getAddress());
        requestParams.add("city", userCredentials.getCity());
        requestParams.add("state", userCredentials.getState());
        requestParams.add("email", userCredentials.getEmail());
        requestParams.add("phone", userCredentials.getPhone());

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + bearerToken);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestParams, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<DeleteResponse> response = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, DeleteResponse.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return (ResponseEntity<T>) ResponseEntity.ok(new DeleteResponse("Successfully Updated"));
        } else if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            return (ResponseEntity<T>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new DeleteResponse("UUID not found"));
        } else {
            return (ResponseEntity<T>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new DeleteResponse("Error occurred during update"));
        }
    }
}

