package com.example.fahad_pc.testscheduleapp.evernote;

import android.app.Application;

import com.evernote.android.job.JobManager;

/**
 * Created by Fahad-PC on 2/6/2018.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        JobManager.create(this).addJobCreator(new DemoJobCreator());
    }
}
