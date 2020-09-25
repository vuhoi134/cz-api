package com.chozoi.authenservice.domain.repositories;

import com.chozoi.authenservice.domain.entities.Profile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {

    Profile save(Profile profile);


}
