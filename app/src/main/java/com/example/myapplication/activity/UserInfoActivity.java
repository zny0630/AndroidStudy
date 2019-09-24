package com.example.myapplication.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.example.helloworld.R;
import com.example.myapplication.entity.UserInfo;
import com.example.myapplication.entity.UserInfo;
import com.example.myapplication.service.UserInfoService;
import com.example.myapplication.service.UserInfoServiceImpl;

import java.util.Arrays;
import java.util.List;

public class user_info_Activity extends AppCompatActivity implements View.OnClickListener {

    //1.定义界面上的控件对象
    private TextView tvNickname, tvSignature, tvUsername, tvSex;
    private LinearLayout nicknameLayout, sexLayout, signatureLayout;
    private Toolbar toolbar;

    private UserInfo user;
    private UserInfoService service;
    private String spUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        initToolbar();
        initData();
        //5.获取所有控件对象，设置监听器（必须）
        initView();
    }

    private void initToolbar() {
        toolbar = findViewById(R.id.title_toolbar);
        toolbar.setTitle("个人信息");
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);//设置返回键
//            actionBar.setHomeButtonEnabled(true);//设置是否是首页
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_info_Activity.this.finish();
            }
        });
    }

    private void initView() {
        tvUsername = findViewById(R.id.tv_username);
        tvNickname = findViewById(R.id.tv_username);
        tvSignature = findViewById(R.id.time);
        tvSex = findViewById(R.id.tv_version);


        //设置数据库获取的数据
        tvUsername.setText(user.getUsername());
        tvNickname.setText(user.getNickname());
        tvSex.setText(user.getSex());


        //设置监听器
        nicknameLayout.setOnClickListener(this);
        sexLayout.setOnClickListener(this);
        signatureLayout.setOnClickListener(this);


    }

    private void initData() {

        service = new UserInfoServiceImpl(this);
        user = service.get(spUsername);
        if (user == null) {
            user = new UserInfo();

            user.setUsername(spUsername);
            user.setNickname("课程助手");
            user.setSignature("课程助手");
            user.setSex("男");
            service.save(user);
        }

    }


    private String readUserInfo() {
        SharedPreferences sp = getSharedPreferences("userInfo", MODE_PRIVATE);
        return sp.getString("loginUser", "");
    }

    @Override
    public void onClick(View view) {
        //根据id分别进行事件处理
        switch (view.getId()) {
            case R.id.tv_username:
                //将昵称，性别等信息传给ChangeUserInfoActivity进行修改保存并返回
                modifyNickname();
                break;
            case R.id.tv_version:
                //通过对话框修改
                modifySex();
                break;
        }
    }

    private void modifyNickname() {
        //获取已有的内容
        String nickname = tvNickname.getText().toString();
        Intent intent = new Intent(user_info_Activity.this, change_user_infoAcitivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("title", "设置昵称");//标题栏的标题
        bundle.putString("value", nickname);//内容
        bundle.putInt("flag", 1);//用于区分修改昵称还是签名
        intent.putExtras(bundle);
        //启动下一个界面
        startActivityForResult(intent, 1);
    }

    private void modifySignature() {
        //获取已有的内容
        String signature = tvSignature.getText().toString();
        Intent intent = new Intent(user_info_Activity.this, change_user_infoAcitivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("title", "设置签名");//标题栏的标题
        bundle.putString("value", signature);//内容
        bundle.putInt("flag", 2);//用于区分修改昵称还是签名
        intent.putExtras(bundle);
        //启动下一个界面
        startActivityForResult(intent, 1);
    }

    private void modifySex() {
        final String[] datas = {"男","女"};
        String sex = tvSex.getText().toString();

        final List<String> sexs = Arrays.asList(datas);
        int selected = sexs.indexOf(sex);
        new AlertDialog.Builder(this)
                .setTitle("性别")
                .setSingleChoiceItems(datas, selected, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String sex = datas[which];
                        tvSex.setText(sex);
                        user.setSex(sex);
                        service.modify(user);
                        dialog.dismiss();
                    }
                }).show();
    }
}

