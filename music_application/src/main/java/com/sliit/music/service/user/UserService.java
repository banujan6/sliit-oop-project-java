package com.sliit.music.service.user;

import com.sliit.music.dto.Response;
import com.sliit.music.model.User;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public interface UserService {
  Response saveUser(User user) throws NoSuchProviderException, NoSuchAlgorithmException;
  Response login(String username, String password) throws NoSuchProviderException, NoSuchAlgorithmException;
  Response getAllUser();
}
