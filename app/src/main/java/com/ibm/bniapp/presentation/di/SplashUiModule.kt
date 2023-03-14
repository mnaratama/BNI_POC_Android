package com.ibm.bniapp.presentation.di

import com.ibm.bniapp.presentation.viewmodel.SplashViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SplashUiModule {

    @Provides
    @Singleton
    fun provideSplashViewModel() : SplashViewModel = SplashViewModel()
}