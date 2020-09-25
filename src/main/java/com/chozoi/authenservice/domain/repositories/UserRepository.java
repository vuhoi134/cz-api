package com.chozoi.authenservice.domain.repositories;

import com.chozoi.authenservice.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.relational.core.sql.In;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
      @Query("select count (u.id) from dbo_user u")
      long getTotalUser();

      Optional<User> findByUserName(String userName);



      User save(User user);

}
