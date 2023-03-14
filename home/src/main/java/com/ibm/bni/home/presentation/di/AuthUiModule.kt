package com.ibm.bni.home.presentation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthUiModule {

//    @Provides
//    @Singleton
//    fun provideAuthViewModel(authRepository: AuthRepository,
//                               createOtpUseCase: CreateOtpUseCase)
//    : AuthViewModel = AuthViewModel(authRepository = authRepository, createOtpUseCase = createOtpUseCase)
}