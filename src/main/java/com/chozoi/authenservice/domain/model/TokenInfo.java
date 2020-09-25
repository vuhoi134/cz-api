package com.chozoi.authenservice.domain.model;

import lombok.Data;

@Data
public class TokenInfo {
    private int userId;
    private String name;
    private int categoryID;
}
