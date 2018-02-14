package com.suku.learningkids.features.splash;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.suku.learningkids.R;
import com.suku.learningkids.features.home.HomeActivity;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    private static final int MSG_DISPLAY_NEXT_CHARACTER = 101;
    private static final int MSG_DISPLAY_NEXT_SCREEN = 102;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        startZoomInAnimation();
    }

    private void startZoomInAnimation(){
        ImageView imageView = findViewById(R.id.iv_splash);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in_animation);
        imageView.startAnimation(animation);

        settext(getString(R.string.app_name));

    }

    private void settext(final String s){
        final TextView textView1 = findViewById(R.id.tv_text1);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/kid1.ttf");
        textView1.setTypeface(custom_font);
        final int[] i = new int[1];
        i[0] = 0;
        final int length = s.length();
        final Handler handler = new Handler()
        {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what == MSG_DISPLAY_NEXT_CHARACTER){
                    char c= s.charAt(i[0]);
                    Log.d("Strange",""+c);
                    textView1.append(String.valueOf(c));
                    i[0]++;
                }else if(msg.what == MSG_DISPLAY_NEXT_SCREEN){
                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                }

            }
        };

        final Timer timer = new Timer();
        TimerTask taskEverySplitSecond = new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(MSG_DISPLAY_NEXT_CHARACTER);
                if (i[0] == length - 1) {
                    handler.sendEmptyMessage(MSG_DISPLAY_NEXT_SCREEN);
                    timer.cancel();
                }
            }
        };
        timer.schedule(taskEverySplitSecond, 1, 300);
    }
}
