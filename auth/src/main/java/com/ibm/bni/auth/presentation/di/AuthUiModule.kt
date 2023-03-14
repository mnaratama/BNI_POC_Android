package com.ibm.bni.auth.presentation.di

import com.ibm.bni.auth.domain.repository.AuthRepository
import com.ibm.bni.auth.presentation.viewmodel.AuthViewModel
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
//                               generateOtpUseCase: GenerateOtpUseCase)
//    : AuthViewModel = AuthViewModel(authRepository = authRepository, generateOtpUseCase = generateOtpUseCase)
}