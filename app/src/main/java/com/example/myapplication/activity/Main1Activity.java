package com.example.myapplication.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.helloworld.R;
import com.example.myapplication.fragment.MySettingFragment;
import com.example.myapplication.fragment.PractiseFragment;

public class Main1Activity extends AppCompatActivity {
    private Toolbar toolbar;
    private RadioButton rbHome;
    private RadioButton rbFind;
    private RadioButton rbMy;
    private RadioButton rbWeb;
    private RadioGroup rgNav;
    private SparseArray<String> titles;
    private SparseArray<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initTitles();
        initFragments();
        rgNav.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                replaceFragment(fragments.get(checkedId));
                switch (checkedId){
                    case R.id.rb_home:
                        Toast.makeText(Main1Activity.this,titles.get(R.id.rb_home),Toast.LENGTH_SHORT).show();
                        toolbar = findViewById(R.id.title_bar);
                        toolbar.setTitle(titles.get(R.id.rb_home));
                        toolbar.setVisibility(View.VISIBLE);
                        setSupportActionBar(toolbar);
                        break;
                    case R.id.rb_find:
                        Toast.makeText(Main1Activity.this,titles.get(R.id.rb_find),Toast.LENGTH_SHORT).show();
                        toolbar = findViewById(R.id.title_bar);
                        toolbar.setTitle(titles.get(R.id.rb_find));
                        toolbar.setVisibility(View.VISIBLE);
                        setSupportActionBar(toolbar);
                        break;
                    case R.id.rb_web:
                        Toast.makeText(Main1Activity.this,titles.get(R.id.rb_web),Toast.LENGTH_SHORT).show();
                        toolbar = findViewById(R.id.title_bar);
                        toolbar.setTitle(titles.get(R.id.rb_web));
                        toolbar.setVisibility(View.VISIBLE);
                        setSupportActionBar(toolbar);
                        break;
                    case R.id.rb_my:
                        Toast.makeText(Main1Activity.this,titles.get(R.id.rb_my),Toast.LENGTH_SHORT).show();
                        toolbar.setVisibility(View.GONE);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void initFragments() {
        //1.创建fragment列表
        fragments = new SparseArray<>();
        fragments.put(R.id.rb_my, MySettingFragment.newInstance());
        fragments.put(R.id.rb_find, PractiseFragment.newInstance("Activity向Fragment传值"));
        //加载默认的fragment
        replaceFragment(fragments.get(R.id.rb_my));
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.fragment_main,fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    private void initView() {
        rbHome = findViewById(R.id.rb_home);
        rbFind = findViewById(R.id.rb_find);
        rbMy = findViewById(R.id.rb_my);
        rbWeb = findViewById(R.id.rb_web);
        rgNav = findViewById(R.id.radio_main);
    }

    private void initTitles(){
        titles = new SparseArray<>();
        titles.put(R.id.rb_home,"课程");
        titles.put(R.id.rb_find,"习题");
        titles.put(R.id.rb_web,"资讯");
        titles.put(R.id.rb_my,"我的");
    }
}
