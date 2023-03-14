package com.ibm.bni.home.data.di

import com.ibm.bni.home.data.remote.api.AuthApi
import com.ibm.bni.home.data.remote.repository.AuthRepositoryImpl
import com.ibm.bni.home.domain.repository.AuthRepository
import com.ibm.bni.home.domain.usecase.CreateOtpUseCase
import com.ibm.bni.home.domain.usecase.createOtp
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    fun provideCreateOtpUseCase(authRepository: AuthRepository) : CreateOtpUseCase {
        return CreateOtpUseCase {
            createOtp(authRepository)
        }
    }

    @Provides
    fun provideAuthApi(retrofit: Retrofit) : AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface BindModule {

        @Binds
        @Singleton
        fun bindPlanRepository(impl : AuthRepositoryImpl) : AuthRepository

    }
}