package com.memo.pcw69.pabixreproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler hd = new Handler();
        hd.postDelayed(new splashhandler() , 2500); // 3초 후에 splashHandler 실행

        ImageView splash_gif = (ImageView) findViewById(R.id.splash_gif);
        GlideDrawableImageViewTarget gifImage = new GlideDrawableImageViewTarget(splash_gif);
        Glide.with(this).load(R.drawable.splash).into(gifImage);
    }

    private class splashhandler implements Runnable{
        public void run() {
            //startActivity(new Intent(getApplication(), LoginActivity.class)); // 로딩이 끝난후 이동할 Activity
            startActivity(new Intent(getApplication(), MainActivity.class));
            SplashActivity.this.finish(); // 로딩페이지 Activity Stack에서 제거
        }
    }

}
