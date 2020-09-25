package com.chozoi.authenservice.domain.entities;

import javax.persistence.*;

import com.chozoi.authenservice.domain.entities.products.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity(name = "dbo_user")
public class User {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  @Id
  private int id;

  @Column(name = "username", unique = true)
  private String userName;

  @Column(name = "password")
  private String passWord;

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "prolife_id")
  private Profile profile;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
  private List<Product> productList = new ArrayList<>();

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassHash() {
    return passWord;
  }

  public void setPassHash(String passHash) {
    this.passWord = passHash;
  }

  public Profile getProfile() {
    return profile;
  }

  public void setProfile(Profile profile) {
    this.profile = profile;
  }
}
