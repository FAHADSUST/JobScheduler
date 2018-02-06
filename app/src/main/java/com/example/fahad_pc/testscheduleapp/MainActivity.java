package com.example.fahad_pc.testscheduleapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fahad_pc.testscheduleapp.evernote.LifeEvernoteJobScheduler;

public class MainActivity extends AppCompatActivity {


    LifeJobScheduler lifeJobScheduler;
    LifeEvernoteJobScheduler lifeEvernoteJobScheduler;

    Button simpleBtn;
    Button complexBtn;

    int Type = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        simpleBtn = (Button) findViewById(R.id.button);
        complexBtn = (Button) findViewById(R.id.button2);

        lifeJobScheduler = new LifeJobScheduler(this);
        lifeEvernoteJobScheduler = new LifeEvernoteJobScheduler();



        simpleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Type==1)
                {
                    lifeJobScheduler.scheduleSimpleJob("1");
                }else{
                    lifeEvernoteJobScheduler.runJobImmediately();
                }
            }
        });


        complexBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentTimeStampInSec = (int)(System.currentTimeMillis()/1000);

                if(Type==1)
                {
                    lifeJobScheduler.scheduleComplexJob("2", currentTimeStampInSec + (20*10));
                }else{
                    lifeEvernoteJobScheduler.scheduleExactJob();
                }
            }
        });




    }



}
