package com.example.fahad_pc.testscheduleapp;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.example.fahad_pc.testscheduleapp.MyJobService;
import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Trigger;

/**
 * Created by Fahad-PC on 2/6/2018.
 */

public class LifeJobScheduler {

    FirebaseJobDispatcher dispatcher;

    public LifeJobScheduler(Context context) {
        dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(context));
    }

    public void scheduleSimpleJob(String uniqueTag)
    {
        Job myJob = dispatcher.newJobBuilder()
                .setService(MyJobService.class) // the JobService that will be called
                .setTag(uniqueTag)        // uniquely identifies the job
                .build();

        dispatcher.mustSchedule(myJob);
    }

    public void scheduleComplexJob(String uniqueTag, int utcTimeStamp)
    {
        int timeStamp = (utcTimeStamp - (int)(System.currentTimeMillis()/1000));

        Log.e("LifeJobScheduler", "start : time stamp: " + utcTimeStamp + ", timeDiff: " + timeStamp+", shouldFire: " + (utcTimeStamp+timeStamp));

        Bundle myExtrasBundle = new Bundle();
        myExtrasBundle.putString("some_key", "some_value");

        Job myJob = dispatcher.newJobBuilder()
                // the JobService that will be called
                .setService(MyJobService.class)
                // uniquely identifies the job
                .setTag(uniqueTag)
                // one-off job
                .setRecurring(false)
                // don't persist past a device reboot
                .setLifetime(Lifetime.UNTIL_NEXT_BOOT)
                // start between 0 and 60 seconds from now
                .setTrigger(Trigger.executionWindow(timeStamp, timeStamp + 20))
                // don't overwrite an existing job with the same tag
                .setReplaceCurrent(false)
                // retry with exponential backoff
                .setRetryStrategy(RetryStrategy.DEFAULT_EXPONENTIAL)
                // constraints that need to be satisfied for the job to run
                .setConstraints(
                        // only run on an unmetered network
                        Constraint.ON_UNMETERED_NETWORK,
                        // only run when the device is charging
                        Constraint.DEVICE_CHARGING
                )
                .setExtras(myExtrasBundle)
                .build();

        dispatcher.mustSchedule(myJob);
    }

    public void cancelJob(String uniqueTag)
    {
        dispatcher.cancel(uniqueTag);
    }

    public void cancelAllJob()
    {
        dispatcher.cancelAll();
    }
}
