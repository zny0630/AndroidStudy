package com.example.myapplication.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.helloworld.R;
import com.example.myapplication.entity.UserInfo;
import com.example.myapplication.service.UserInfoService;
import com.example.myapplication.service.UserInfoServiceImpl;

public class user_info_Activity extends AppCompatActivity {

    //1.定义界面上的控件对象
    private TextView tvNickname,tvSignature,tvUsername,tvSex;
    private LinearLayout nicknameLayout,sexLayout,signatureLayout;

    private UserInfo user;
    private UserInfoService service;
    private String spUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        initData();
        //5.获取所有控件对象，设置监听器（必须）
        initView();
    }

    private void initView() {
        tvUsername = findViewById(R.id.tv_username);
        tvNickname = findViewById(R.id.tv_nickname);
        tvSignature = findViewById(R.id.tv_signature);
        tvSex = findViewById(R.id.tv_sex);

        nicknameLayout =findViewById(R.id.ll_nickname);
        sexLayout = findViewById(R.id.ll_sex);
        signatureLayout = findViewById(R.id.ll_signature);




    }

    private void initData() {

        service = new UserInfoServiceImpl(this);
        user = service.get(spUsername);
        if(user == null){
            user = new UserInfo();

            user.setUsername(spUsername);
            user.setNickname("课程助手");
            user.setSignature("课程助手");
            user.setSex("男");
            service.save(user);
        }

    }




    private String readUserInfo() {
        SharedPreferences sp = getSharedPreferences("userInfo",MODE_PRIVATE);
        return sp.getString("loginUser","");
    }
}
