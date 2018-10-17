package com.yangys.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.yangys.fragment.AboutFragment;
import com.yangys.fragment.CategoriesFragment;
import com.yangys.fragment.timeline.TimeLineFragment;

import example.com.myandroid.R;

public class MainActivity extends AppCompatActivity {


    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private BottomNavigationView bottomNavigationView;

    private TimeLineFragment timeLineFragment;
    private CategoriesFragment categoriesFragment;
    private AboutFragment aboutFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initFragment();
    }

    private void initView(){
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navi_view);
        setSupportActionBar(toolbar);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.bot_nav_timeline:
                                setFragment(timeLineFragment);
                                break;
                            case R.id.bot_nav_categories:
                                setFragment(categoriesFragment);
                                break;
                            case R.id.bot_nav_help:
                                setFragment(aboutFragment);
                                break;
                        }
                        return true;
                    }
                });
    }

    private void initFragment(){
        timeLineFragment = new TimeLineFragment();
        categoriesFragment = new CategoriesFragment();
        aboutFragment = new AboutFragment();
        FragmentManager fm = getSupportFragmentManager();
        if(!timeLineFragment.isAdded()){
            fm.beginTransaction().add(R.id.frame_layout,timeLineFragment,TimeLineFragment.class.getSimpleName())
                    .commit();
        }
        if(!categoriesFragment.isAdded()){
            fm.beginTransaction().add(R.id.frame_layout,categoriesFragment,CategoriesFragment.class.getSimpleName())
                    .commit();
        }
        if(!aboutFragment.isAdded()){
            fm.beginTransaction().add(R.id.frame_layout,aboutFragment,AboutFragment.class.getSimpleName())
                    .commit();
        }
        setFragment(timeLineFragment);
    }

    private void setFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        if(fragment instanceof TimeLineFragment){
            fm.beginTransaction()
                    .show(timeLineFragment)
                    .hide(categoriesFragment)
                    .hide(aboutFragment)
                    .commit();
            setTitle(R.string.timeline_label);
        }else if(fragment instanceof CategoriesFragment){
            fm.beginTransaction()
                    .show(categoriesFragment)
                    .hide(timeLineFragment)
                    .hide(aboutFragment)
                    .commit();
            setTitle(R.string.categories_label);
        }else if(fragment instanceof AboutFragment){
            fm.beginTransaction()
                    .show(aboutFragment)
                    .hide(timeLineFragment)
                    .hide(categoriesFragment)
                    .commit();
            setTitle(R.string.nav_about);
        }
    }

    public void moveToLoginActivity(){
        Intent intent  = new Intent();
        intent.setClass(this,LoginMainActivity.class);
        startActivity(intent);
        finish();
    }
}
