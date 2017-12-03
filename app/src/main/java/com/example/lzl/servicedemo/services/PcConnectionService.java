package com.example.lzl.servicedemo.services;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.OutputStream;

/**
 * Created by lzl on 17-12-2.
 */

public class PcConnectionService extends Service {
    private MyBinder binder;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            System.out.println("PC onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            System.out.println("PC onServiceDisconnected");
        }
    };


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("onBind");
        Intent intent1 = new Intent(this,AnotherServiceTest.class);
        bindService(intent1,connection, Context.BIND_AUTO_CREATE);
        return binder;
    }


    @Override
    public void onCreate() {
        binder = new MyBinder();
        System.out.println("onCreate");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        System.out.println("onDestroyed");
        unbindService(connection);
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        System.out.println("onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    public class MyBinder extends Binder{

    }

}
