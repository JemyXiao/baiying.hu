package com.baiying.hu.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Long id;

    private String username;

    private String password;

    private String token;

    private Date createdAt;

    private Date updatedAt;

    private Long phone;

    private String nickName;

    private Integer cityId;

    private String status;
}