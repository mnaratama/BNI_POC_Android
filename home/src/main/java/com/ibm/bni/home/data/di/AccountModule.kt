package com.ibm.bni.home.data.di

import com.ibm.bni.home.data.remote.api.AccountApi
import com.ibm.bni.home.data.remote.repository.AccountRepositoryImpl
import com.ibm.bni.home.domain.repository.AccountRepository
import com.ibm.bni.home.domain.usecase.AccountUseCase
import com.ibm.bni.home.domain.usecase.listTransaction
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AccountModule {

    @Provides
    fun provideAccountUseCase(accountRepository: AccountRepository): AccountUseCase
    {
        return AccountUseCase {
            listTransaction(accountRepository)
        }
    }

    @Provides
    fun provideHomAccountApi(retrofit: Retrofit) : AccountApi {
        return retrofit.create(AccountApi::class.java)
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface BindModule {

        @Binds
        @Singleton
        fun bindPlanRepository(impl : AccountRepositoryImpl) : AccountRepository

    }
}