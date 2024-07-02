package com.example.uchat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    ImageView logo;
    TextView slogan;
    Animation anim_logo,anim_slogan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        delaySplashScreen();
        setAnimation();
    }
    private void setAnimation(){
        anim_logo= AnimationUtils.loadAnimation(this,R.anim.animation_logo);
        anim_slogan=AnimationUtils.loadAnimation(this,R.anim.animation_slogan);
        logo.setAnimation(anim_logo);
        slogan.setAnimation(anim_slogan);
    }
    private void delaySplashScreen(){
        new Handler().postDelayed((Runnable) () -> {
            if(FirebaseUtil.isLoggedIn()){
                Intent intent=new Intent(MainActivity.this,Home.class);
                startActivity(intent);
            }
            else {
                Intent intent=new Intent(MainActivity.this,LoginNumber.class);
                startActivity(intent);
            }
            finish();
        },2000);
    }
    private void init(){
        logo=findViewById(R.id.logo);
        slogan=findViewById(R.id.slogan);
    }

}