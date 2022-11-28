package com.code.jamie.noteme.di.modules

import com.code.jamie.noteme.ui.viewmodels.MainViewModel
import dagger.Component
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Singleton
@InstallIn(SingletonComponent::class)
@Component(modules = [AppModule::class])
interface AppComponent {
    fun injectMainViewModel(mainViewModel: MainViewModel)
}