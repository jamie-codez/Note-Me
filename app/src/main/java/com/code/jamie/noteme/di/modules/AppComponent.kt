package com.code.jamie.noteme.di.modules

import com.code.jamie.noteme.ui.viewmodels.MainViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun injectMainViewModel(mainViewModel: MainViewModel)
}