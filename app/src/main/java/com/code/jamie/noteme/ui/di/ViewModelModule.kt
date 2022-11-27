package com.code.jamie.noteme.ui.di

import com.code.jamie.noteme.di.ViewModelKey
import com.code.jamie.noteme.ui.viewmodels.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    abstract fun bindMainViewModel(mainViewModel: MainViewModel)
}