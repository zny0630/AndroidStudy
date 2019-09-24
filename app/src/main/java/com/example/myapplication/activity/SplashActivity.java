package com.example.myapplication.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.helloworld.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {
    private TextView textView;  //控件对象
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //1.获取需要的控件
        textView = findViewById(R.id.tv_version);
        //2.获取或设置控件的值
        String version = textView.getText().toString();
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(),0);
            textView.setText("版本号：" + info.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        //3.跳转界面
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                //不带数据的页面跳转
                Intent intent = new Intent(SplashActivity.this,Main1Activity.class);
                startActivity(intent);
                SplashActivity.this.finish();
            }
        };
        timer.schedule(task,1000);
    }
}
