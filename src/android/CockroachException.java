package com.landdigital;

import android.app.Activity;
import android.content.Intent;
import android.content.Context;
// import android.widget.Toast;
import android.app.AlarmManager;
import android.app.PendingIntent;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;
import java.lang.Thread.UncaughtExceptionHandler;

public class CockroachException implements Thread.UncaughtExceptionHandler {
    private CordovaInterface cordova;
    protected Thread.UncaughtExceptionHandler defaultExceptionHandler;

    public CockroachException(CordovaInterface cordova) {
        // store reference to cordova
        this.cordova = cordova;

        // Store the current defaultExceptionHandler
        this.defaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
    }

    @Override
    public void uncaughtException(
        Thread thread,
        Throwable throwable
    ) {
        // Toast.makeText(this.cordova.getActivity().getApplicationContext(), "Cockroach caught a crash!", Toast.LENGTH_LONG).show();

        // Create a new intent to reload the app.
        Intent intent = new Intent(this.cordova.getActivity(), this.cordova.getActivity().getClass());

        // Add some flags to clean things up.
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

        // Create a pending intent.
        PendingIntent pendingIntent = PendingIntent.getActivity(this.cordova.getActivity().getApplicationContext(), 0, intent, PendingIntent.FLAG_ONE_SHOT);

        // Create a new alarm instance to boot the app
        AlarmManager mgr = (AlarmManager) this.cordova.getActivity().getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 1000, pendingIntent);

        // Pass the exception back to the default handler we stored.
        // NOTE: this will cause the app to crash! But without it, crashlytics etc
        // won't be called.
        if (this.defaultExceptionHandler != null) {
            this.defaultExceptionHandler.uncaughtException(thread, throwable);
        }

        // Close.
        this.cordova.getActivity().finish();
        System.exit(0);
    }
}
