package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    ImageButton imageBtn;
    private CountDownTimer countDownTimer;

    private Button stopButton;
    private Button cancleButton;

    private boolean timerRunning;
    private boolean firstState;
    private long time=0;
    private long tempTime=0;

    FrameLayout setting;
    FrameLayout timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.countdown_text);
        imageBtn = (ImageButton)findViewById(R.id.imageBtn);
        stopButton = (Button)findViewById(R.id.stop_btn);
        cancleButton = (Button)findViewById(R.id.cancle_btn);

        setting = findViewById(R.id.choose);
        timer = findViewById(R.id.timer);
        imageBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                firstState = true;
                setting.setVisibility(setting.GONE);
                timer.setVisibility(timer.VISIBLE);
                startStop();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startStop();
            }
        });

        cancleButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setting.setVisibility(setting.VISIBLE);
                timer.setVisibility(timer.GONE);
                firstState=true;
                stopTimer();
            }
        });
        updateTimer();
    }
    private void startStop(){
        if(timerRunning){
            stopTimer();
        }else{
            startTimer();
        }
    }
    private void startTimer(){
        if(firstState){
            String sHour = "0";
            String sMin = "50";
            String sSec = "00";
            time = (Long.parseLong(sHour)*3600000) + (Long.parseLong(sMin)*60000) + (Long.parseLong(sSec)*1000) + 1000;
        }else{
            time = tempTime;
        }
        countDownTimer = new CountDownTimer(time, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                tempTime = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() { }
        }.start();
        stopButton.setText("일시정지");
        timerRunning = true;
        firstState = false;
    }
    private void stopTimer(){
        countDownTimer.cancel();
        timerRunning = false;
        stopButton.setText("계속");
    }

    private void updateTimer(){
        int hour = (int)tempTime / 3600000;
        int minutes = (int)tempTime % 3600000 / 60000;
        int secounds = (int) tempTime % 3600000 % 60000 / 1000;

        String timeLeftText = "";
        timeLeftText = ""+ hour + ":";
        if(minutes < 10) timeLeftText +="0";
        timeLeftText +=minutes +":";
        if(secounds < 10) timeLeftText +="0";
        timeLeftText +=secounds;

        textView.setText(timeLeftText);

    }

}