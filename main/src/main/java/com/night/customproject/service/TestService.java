package com.night.customproject.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class TestService extends Service {

    private boolean runningFlat = false;
    private String data = "This is default data";

    public TestService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
        return new Binder();
    }

    public class Binder extends android.os.Binder {
        public void setData(String data) {
            TestService.this.data = data;
        }

        public TestService getService() {
            return TestService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("Night", "----onCreate---");
        runningFlat = true;
        new Thread(){
            @Override
            public void run() {
                super.run();
                int i = 0;
                while (runningFlat) {
                    i++;
                    String str = i + ":" + "Service is running.....";
                    Log.e("Night", str);

                    if (callback!= null) {
                        callback.onDataChange(str);
                    }

                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("Night", "-----onStartCommand----");
//        data = intent.getStringExtra("data");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("Night", "-----onDestroy-----");
        runningFlat = false;
    }

    private Callback callback = null;

    public Callback getCallback() {
        return callback;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public interface Callback {
        void onDataChange(String data);
    }
}
