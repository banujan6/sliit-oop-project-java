package com.sliit.music.controller;

import com.sliit.music.dto.Response;
import com.sliit.music.model.User;
import com.sliit.music.service.user.UserService;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
  @Autowired
  private UserService userService;

  @GetMapping(path = "/test")
  public @ResponseBody
  ResponseEntity<String> welcomeUser() {
    return ResponseEntity.ok("Hi, this is music store.");
  }


  @PostMapping(path = "/user")
  public @ResponseBody ResponseEntity<Response> createUser(@RequestBody User user)
      throws NoSuchProviderException, NoSuchAlgorithmException {
    return ResponseEntity.ok(userService.saveUser(user));
  }

  @PostMapping(path = "/login")
  public @ResponseBody ResponseEntity<Response> login(@RequestBody User user)
      throws NoSuchProviderException, NoSuchAlgorithmException {
    return ResponseEntity.ok(userService.login(user.getEmail(), user.getPassword()));
  }

  @GetMapping(path = "/users")
  public @ResponseBody ResponseEntity<Response> listUsers() {
    return ResponseEntity.ok(userService.getAllUser());
  }

}
