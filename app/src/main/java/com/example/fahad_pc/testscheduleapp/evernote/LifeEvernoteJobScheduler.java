package com.example.fahad_pc.testscheduleapp.evernote;

import com.evernote.android.job.JobManager;
import com.evernote.android.job.JobRequest;
import com.evernote.android.job.util.support.PersistableBundleCompat;

import java.util.concurrent.TimeUnit;

/**
 * Created by Fahad-PC on 2/6/2018.
 */

public class LifeEvernoteJobScheduler {

    private void scheduleAdvancedJob() {
        PersistableBundleCompat extras = new PersistableBundleCompat();
        extras.putString("key", "Hello world");

        int jobId = new JobRequest.Builder(DemoSyncJob.TAG)
                .setExecutionWindow(30_000L, 40_000L)
                .setBackoffCriteria(5_000L, JobRequest.BackoffPolicy.EXPONENTIAL)
                .setRequiresCharging(true)
                .setRequiresDeviceIdle(false)
                .setRequiredNetworkType(JobRequest.NetworkType.CONNECTED)
                .setExtras(extras)
                .setRequirementsEnforced(true)
                .setUpdateCurrent(true)
                .build()
                .schedule();
    }

    private void schedulePeriodicJob() {
        int jobId = new JobRequest.Builder(DemoSyncJob.TAG)
                .setPeriodic(TimeUnit.MINUTES.toMillis(15), TimeUnit.MINUTES.toMillis(5))
                .build()
                .schedule();
    }

    public void scheduleExactJob() {
        int jobId = new JobRequest.Builder(DemoSyncJob.TAG)
                .setExact(20_000L)
                .build()
                .schedule();
    }

    public void runJobImmediately() {
        int jobId = new JobRequest.Builder(DemoSyncJob.TAG)
                .startNow()
                .build()
                .schedule();
    }

    private void cancelJob(int jobId) {
        JobManager.instance().cancel(jobId);
    }

    public void testCancelAll() {
        JobManager.instance().cancelAll();
    }

}
