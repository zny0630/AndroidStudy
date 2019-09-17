package com.example.myapplication.service;


import com.example.myapplication.entity.UserInfo;

public interface UserInfoService {
    UserInfo get(String username);
    void save(UserInfo userInfo);
    void modify(UserInfo userInfo);
}
