package com.example.lzl.servicedemo

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder

import com.example.lzl.servicedemo.services.PcConnectionService
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class MainActivity : AppCompatActivity() {

    val serviceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            println("onServiceDisconnected ${name?.toShortString()}")
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            println("onServiceConnected ${name?.toShortString()}")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUI().setContentView(this)
    }

    private fun startMyService(){
        val intent = Intent(this,PcConnectionService::class.java)
        bindService(intent,serviceConnection,Context.BIND_AUTO_CREATE)
    }

    private fun stopMyService(){
        unbindService(serviceConnection)
    }

    inner class MainActivityUI : AnkoComponent<MainActivity>{
        override fun createView(ui: AnkoContext<MainActivity>) = ui.apply {
            verticalLayout {
                val startService = button("Start Service"){
                    onClick { startMyService() }
                }
                val stopService = button("Stop Service"){
                    onClick { stopMyService() }
                }
            }
        }.view
    }
}
