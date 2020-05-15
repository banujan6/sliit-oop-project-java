package com.sliit.music.service.user.impl;

import com.sliit.music.dto.Response;
import com.sliit.music.exceptions.MusicStoreException;
import com.sliit.music.model.Album;
import com.sliit.music.model.User;
import com.sliit.music.reopository.UserRepository;
import com.sliit.music.service.user.UserService;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  byte[] salt = getSalt();

  public UserServiceImpl() throws NoSuchProviderException, NoSuchAlgorithmException {
  }

  public Response saveUser(User user) throws NoSuchProviderException, NoSuchAlgorithmException {
    user.setPassword(getSecurePassword(user.getPassword(), salt));
    User savedUser = userRepository.save(user);
    return new Response("00", "user successfully saved!", savedUser);
  }

  public Response login(String username, String password)
      throws NoSuchProviderException, NoSuchAlgorithmException {

    User user = userRepository.findByEmail(username);
    if (!user.getEmail().equals(username)) {
      throw new MusicStoreException("Invalid user");
    }
    System.out.println("user password: " +password);
    System.out.println("hashed password: " +getSecurePassword(password, salt));
    if(!getSecurePassword(password, salt).equals(user.getPassword())) {
      throw new MusicStoreException("Invalid password");
    }

    user.setPassword(null);

    return new Response("00", "successfully logged in", user);
  }

  private static String getSecurePassword(String passwordToHash, byte[] salt)
  {
    String generatedPassword = null;
    try {
      // Create MessageDigest instance for MD5
      MessageDigest md = MessageDigest.getInstance("MD5");
      //Add password bytes to digest
      md.update(salt);
      //Get the hash's bytes
      byte[] bytes = md.digest(passwordToHash.getBytes());
      //This bytes[] has bytes in decimal format;
      //Convert it to hexadecimal format
      StringBuilder sb = new StringBuilder();
      for(int i=0; i< bytes.length ;i++)
      {
        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
      }
      //Get complete hashed password in hex format
      generatedPassword = sb.toString();
    }
    catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return generatedPassword;
  }

  //Add salt
  private static byte[] getSalt() throws NoSuchAlgorithmException, NoSuchProviderException
  {
    //Always use a SecureRandom generator
    SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
    //Create array for salt
    byte[] salt = new byte[16];
    //Get a random salt
    sr.nextBytes(salt);
    //return salt
    return salt;
  }

  @Override
  public Response getAllUser() {
    List<User> users = userRepository.findAll();
    return new Response("00", "", users);
  }

}
