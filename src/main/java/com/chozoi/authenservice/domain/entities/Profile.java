package com.chozoi.authenservice.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity(name = "dbo_prolife")
public class Profile {
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      @Column(name = "id")
      @Id
      private int id;

      @Column(name = "fullname")
      private String fullName;

      @Column(name = "age")
      private int age;

      @Column(name = "birthday")
      private Date birthDay;

      @Column(name = "gender")
      private int gender;

      @Column(name = "email")
      private String email;

      public int getId() {
            return id;
      }

      public void setId(int id) {
            this.id = id;
      }

      public String getFullName() {
            return fullName;
      }

      public void setFullName(String fullName) {
            this.fullName = fullName;
      }

      public int getAge() {
            return age;
      }

      public void setAge(int age) {
            this.age = age;
      }

      public Date getBirthDay() {
            return birthDay;
      }

      public void setBirthDay(Date birthDay) {
            this.birthDay = birthDay;
      }

      public int getGender() {
            return gender;
      }

      public void setGender(int gender) {
            this.gender = gender;
      }

      public String getEmail() {
            return email;
      }

      public void setEmail(String email) {
            this.email = email;
      }
}
