package com.example.lzl.servicedemo.services;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by lzl on 17-12-3.
 */

public class AnotherServiceTest extends Service {
    NormalBinder binder;

    @Override
    public void onCreate() {
        binder = new NormalBinder();
        System.out.println("AnotherServiceTest onCreate");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        String name = intent.getStringExtra("name");
        System.out.println("AnotherServiceTest onBind , name="+name);

        return binder;
    }


    @Override
    public void unbindService(ServiceConnection conn) {
        System.out.println("AnotherServiceTest unbindService");
        super.unbindService(conn);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("AnotherServicesTest onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        System.out.println("AnotherServicesTest onDestroy");
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        System.out.println("Another onUnbind!");
        return super.onUnbind(intent);
    }

    public class NormalBinder extends Binder{

    }
}
