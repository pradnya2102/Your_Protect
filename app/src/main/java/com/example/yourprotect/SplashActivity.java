package com.example.yourprotect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {
    private ImageView iv1, iv2;

    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        iv1 = (ImageView) findViewById(R.id.iv1);
        iv2 = (ImageView) findViewById(R.id.iv2);

        animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.push_down);
        iv1.setAnimation(animation);

        animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.push_right);
        iv2.setAnimation(animation);

        Thread thread = new Thread()
        {
            @Override
            public void run() {
                try {

                    sleep(4000);
                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                super.run();

            }
        };
        thread.start();
    }

}