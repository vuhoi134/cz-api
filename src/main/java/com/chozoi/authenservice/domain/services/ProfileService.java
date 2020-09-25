package com.chozoi.authenservice.domain.services;

import com.chozoi.authenservice.app.dto.ProfileDTO;
import com.chozoi.authenservice.domain.ModelMapper;
import com.chozoi.authenservice.domain.entities.Profile;
import com.chozoi.authenservice.domain.exceptions.ResourceNotFoundException;
import com.chozoi.authenservice.domain.model.TokenInfo;
import com.chozoi.authenservice.domain.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class ProfileService extends BaseService {
  //
  @Autowired private ProfileRepository profileRepository;
  @Autowired CacheManagerService cacheManagerService;
  @Autowired private ModelMapper modelMapper;
  //
  @Transactional(isolation = Isolation.SERIALIZABLE)
  public ResponseEntity update(ProfileDTO dto, String token) {
    TokenInfo tokenInfo = cacheManagerService.getToken(token);
    if (Objects.isNull(tokenInfo)) {
      throw new ResourceNotFoundException("Sai token rồi b ơi");
    }
    Profile profile = profileRepository.findById(tokenInfo.getUserId()).orElse(null);
    if (Objects.isNull(profile)) {
      throw new ResourceNotFoundException("Ko tìm thầy bạn ơi");
    }
    profile.setFullName(dto.getFullName());
    profile.setAge(dto.getAge());
    profile.setGender(dto.getGender());
    profile.setEmail(dto.getEmail());
    profile.setBirthDay(dto.getBirthDay());
    profileRepository.save(profile);
    return new ResponseEntity<>("Updated thành công", HttpStatus.OK);
  }

  public ResponseEntity getInfo(String token) {
    TokenInfo tokenInfo = cacheManager.getToken(token);
    if (Objects.isNull(tokenInfo)) {
      throw new ResourceNotFoundException("Sai token rồi b ơi");
    }
    Profile profile = profileRepository.findById(tokenInfo.getUserId()).orElse(null);
    ProfileDTO dto = modelMapper.profileToResponse(profile);
    return ResponseEntity.ok(dto);
  }
}
