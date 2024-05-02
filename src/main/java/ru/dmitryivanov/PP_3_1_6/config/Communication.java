package ru.dmitryivanov.PP_3_1_6.config;

import jakarta.websocket.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.dmitryivanov.PP_3_1_6.model.User;

import java.util.List;

@Component
public class Communication {
    RestTemplate restTemplate = new RestTemplate();
    String URL = "http://94.198.50.185:7081/api/users";
    String SESSION_ID;

    public String getSecretCode() {
        StringBuilder result = new StringBuilder();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

        //Получаем куки
        SESSION_ID = getSESSION_ID(requestEntity);
        headers.add("Cookie", SESSION_ID);

        //Получаем первую часть кода
        User userToSave = new User(3L, "James", "Brown", (byte) 22);
        requestEntity = new HttpEntity<>(userToSave,headers);
        result.append(addUser(requestEntity));

        //Получаем вторую часть кода
        userToSave.setName("Thomas");
        userToSave.setLastName("Shelby");
        requestEntity=new HttpEntity<>(userToSave,headers);
        result.append(editUser(requestEntity));

        //Получаем третью часть кода
        requestEntity=new HttpEntity<>(headers);
        result.append(deleteUser(3,requestEntity));

        return result.toString();
    }

    public String getSESSION_ID(HttpEntity<Object> requestEntity) {
        ResponseEntity<List<User>> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<User>>() {
        });
        return responseEntity.getHeaders().getFirst("Set-Cookie");
    }

    private String deleteUser(int id, HttpEntity<Object> requestEntity) {
        ResponseEntity<String> responseEntityDelete = restTemplate.exchange(URL+"/"+id, HttpMethod.DELETE, requestEntity, String.class);
        return responseEntityDelete.getBody();
    }

    public String addUser(HttpEntity<Object> requestEntity) {
        ResponseEntity<String> responseEntityAdd = restTemplate.exchange(URL, HttpMethod.POST, requestEntity, String.class);
        return responseEntityAdd.getBody();
    }

    public String editUser(HttpEntity<Object> requestEntity) {
        ResponseEntity<String> responseEntityEdit= restTemplate.exchange(URL, HttpMethod.PUT, requestEntity, String.class);
        return responseEntityEdit.getBody();
    }


}
