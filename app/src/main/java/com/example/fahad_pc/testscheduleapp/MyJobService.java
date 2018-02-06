package com.example.fahad_pc.testscheduleapp;

/**
 * Created by Fahad-PC on 2/6/2018.
 */

import android.util.Log;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

public class MyJobService extends JobService {
    @Override
    public boolean onStartJob(JobParameters job) {
        // Do some work here
        int firingTimeStamp = (int)(System.currentTimeMillis()/1000);
        Log.e("MyJobService", "onStartJob------------------- " + firingTimeStamp);
        return false; // Answers the question: "Is there still work going on?"
    }

    @Override
    public boolean onStopJob(JobParameters job) {

        Log.e("MyJobService", "onStopJob-------------------");
        return false; // Answers the question: "Should this job be retried?"
    }
}
