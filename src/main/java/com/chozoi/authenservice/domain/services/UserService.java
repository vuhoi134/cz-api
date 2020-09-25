package com.chozoi.authenservice.domain.services;

import com.chozoi.authenservice.domain.ModelMapper;
import com.chozoi.authenservice.domain.entities.User;
import com.chozoi.authenservice.domain.exceptions.PassNotFoundException;
import com.chozoi.authenservice.domain.exceptions.TokenNotFoundException;
import com.chozoi.authenservice.domain.exceptions.UserNotFoundException;
import com.chozoi.authenservice.domain.model.TokenInfo;
import com.chozoi.authenservice.domain.repositories.ProfileRepository;
import com.chozoi.authenservice.domain.repositories.UserRepository;
import com.chozoi.authenservice.domain.entities.Profile;
import com.chozoi.authenservice.app.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserService extends BaseService {
  @Autowired private CacheManagerService cacheManager;

  @Autowired private UserRepository userRepository;

  @Autowired private ProfileRepository profileRepository;

  @Autowired private ModelMapper modelMapper;
  //     AUTHEN SERVICE
  @Transactional(isolation = Isolation.SERIALIZABLE)
  public ResponseEntity<?> createUser(UserDTO userDTO) {
    Profile profile = Profile.builder().build();
    User user =
        User.builder()
            .userName(userDTO.getUserName())
            .passWord(convertPassword(userDTO.getPassWord()))
            .profile(profile)
            .build();
    userRepository.save(user);
    return new ResponseEntity<>("Chúc mừng bạn đến với CZ", HttpStatus.CREATED);
  }

  public ResponseEntity<?> login(String username, String password) {
    User user = userRepository.findByUserName(username).orElse(null);
    if (Objects.isNull(user)) {
      throw new UserNotFoundException("tài khoản sai rồi");
    }
    if (!matching(password, user.getPassHash())) {
      throw new PassNotFoundException("Mật khẩu sai rồi ");
    }
    String token = generateToken();
    cacheManager.setToken(token, user.getId(), user.getUserName());
    Map<String, String> response = new HashMap<>();
    response.put("token", token);
    return ResponseEntity.ok(response);
  }

  public ResponseEntity logout(String token) {
    TokenInfo tokenInfo = cacheManager.getToken(token);
    if (Objects.isNull(tokenInfo)) {
      throw new TokenNotFoundException("Token bị sai rồi bạn");
    }
    cacheManager.deleteValue(token);
    return new ResponseEntity<>("You have been logged out!", HttpStatus.SEE_OTHER);
  }

  public ResponseEntity getUserInfo(String toekn) {
    TokenInfo tokenInfo = cacheManager.getToken(toekn);
    if (Objects.isNull(tokenInfo)) {
      throw new TokenNotFoundException("Token bị sai rồi bạn");
    }
    User user = userRepository.findById(tokenInfo.getUserId()).orElse(null);
    UserDTO dto = modelMapper.userToResponse(user);
    return new ResponseEntity<>(dto, HttpStatus.FOUND);
  }

  @Transactional(isolation = Isolation.SERIALIZABLE)
  public ResponseEntity deleteUser(String token) {
    TokenInfo tokenInfo = cacheManager.getToken(token);

    if (Objects.isNull(tokenInfo)) {
      throw new TokenNotFoundException("Token bị sai rồi bạn");
    }
    userRepository.deleteById(tokenInfo.getUserId());
    cacheManager.deleteValue(token);
    return new ResponseEntity<>("deleted", HttpStatus.OK);
  }

  public void addNewUser(User user) {
    userRepository.save(user);
  }

  @Transactional(isolation = Isolation.SERIALIZABLE)
  public ResponseEntity update(UserDTO userDTO, String token) {
    TokenInfo tokenInfo = cacheManager.getToken(token);

    if (Objects.isNull(tokenInfo)) {
      throw new TokenNotFoundException("Token bị sai rồi bạn");
    }
    User user = userRepository.findById(tokenInfo.getUserId()).orElse(null);
    if (Objects.isNull(user)) {
      throw new UserNotFoundException("User not fount");
    }

    user.setUserName(userDTO.getUserName());
    user.setPassHash(convertPassword(userDTO.getPassWord()));
    userRepository.save(user);
    return new ResponseEntity<>("Update user success", HttpStatus.OK);
  }

  public long getTotalUser() {
    return userRepository.getTotalUser();
  }

  public List<User> getListAllUser() {
    try {
      return userRepository.findAll();
    } catch (Exception e) {
      return new ArrayList<>();
    }
  }
}
