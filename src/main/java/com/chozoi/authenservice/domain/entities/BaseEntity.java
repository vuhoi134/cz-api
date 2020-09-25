package com.chozoi.authenservice.domain.entities;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class BaseEntity {
      @CreatedDate protected LocalDateTime createdAt = LocalDateTime.now();

      @LastModifiedDate protected LocalDateTime updatedAt = LocalDateTime.now();

      @CreatedBy
      @JoinColumn(name = "createdBy")
      @OneToOne
      private User createdBy;

      @LastModifiedBy
      @JoinColumn(name = "updateBy")
      @OneToOne
      private User updatedBy;
}
