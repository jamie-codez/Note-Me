package com.code.jamie.noteme

import android.app.Application
import com.code.jamie.noteme.di.modules.AppComponent
import com.code.jamie.noteme.di.modules.AppModule
import com.code.jamie.noteme.di.modules.DaggerAppComponent
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NoteMe:Application() {

}