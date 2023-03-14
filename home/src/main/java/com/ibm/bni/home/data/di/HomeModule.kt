package com.ibm.bni.home.data.di

import com.ibm.bni.home.data.remote.api.AuthApi
import com.ibm.bni.home.data.remote.api.HomeApi
import com.ibm.bni.home.data.remote.repository.AuthRepositoryImpl
import com.ibm.bni.home.data.remote.repository.HomeRepositoryImpl
import com.ibm.bni.home.domain.repository.AuthRepository
import com.ibm.bni.home.domain.repository.HomeRepository
import com.ibm.bni.home.domain.usecase.CreateOtpUseCase
import com.ibm.bni.home.domain.usecase.HomeUseCase
import com.ibm.bni.home.domain.usecase.createOtp
import com.ibm.bni.home.domain.usecase.listAccount
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

//    @Provides
//    fun provideCreateOtpUseCase(authRepository: AuthRepository) : CreateOtpUseCase {
//        return CreateOtpUseCase {
//            createOtp(authRepository)
//        }
//    }

    @Provides
    fun provideHomeUseCase(homeRepository: HomeRepository): HomeUseCase
    {
        return HomeUseCase {
            listAccount(homeRepository)
        }
    }

    @Provides
    fun provideHomeApi(retrofit: Retrofit) : HomeApi {
        return retrofit.create(HomeApi::class.java)
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface BindModule {

        @Binds
        @Singleton
        fun bindPlanRepository(impl : HomeRepositoryImpl) : HomeRepository

    }
}