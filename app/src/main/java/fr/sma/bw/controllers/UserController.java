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
  private final UserService userService;
  public UserController(final UserService userService) {
  this.userService = userService;
  }
  @Override
  public ResponseEntity<ListUserResponse> getUsers() {
  final ListUserResponse users = this.userService.retrieveUsers();
  HttpStatus codeRetour = HttpStatus.OK;
  if(users.isEmpty()) {
	  log.debug("Aucun résultat");
	  codeRetour = HttpStatus.NOT_FOUND;
  }
  return new ResponseEntity<>(users, codeRetour);
  }
}
