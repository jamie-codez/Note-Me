package com.code.jamie.noteme

import android.app.Application
import com.code.jamie.noteme.di.modules.AppComponent
import com.code.jamie.noteme.di.modules.AppModule
import com.code.jamie.noteme.di.modules.DaggerAppComponent

class NoteMe:Application() {
    private lateinit var appModule: AppComponent
    override fun onCreate() {
        super.onCreate()
        appModule = DaggerAppComponent.builder()
            .appModule(AppModule())
            .build()
    }
    fun getAppModule():AppComponent{
        return appModule
    }
}