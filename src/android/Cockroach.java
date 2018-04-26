package com.landdigital;

import com.landdigital.CockroachException;
// import android.widget.Toast;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaInterface;


public class Cockroach extends CordovaPlugin
{
    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView)
    {
        super.initialize(cordova, webView);

        // Toast.makeText(cordova.getActivity().getApplicationContext(), "Initialised Cockroach", Toast.LENGTH_LONG).show();

        Thread.setDefaultUncaughtExceptionHandler(
            new CockroachException(this.cordova)
        );
    }
}