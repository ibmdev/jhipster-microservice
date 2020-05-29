package fr.sma.bw.controllers;

import fr.sma.bw.services.UserService;
import fr.sma.svc.bw.service.api.UsersApi;
import fr.sma.svc.bw.service.model.ListUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserController implements UsersApi {
    private transient final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    public ResponseEntity<ListUserResponse> getUsers() {
        ListUserResponse users = this.userService.retrieveUsers();
        return new ResponseEntity<>(users, users.isEmpty() ? HttpStatus.OK:HttpStatus.NOT_FOUND);

    }
}
