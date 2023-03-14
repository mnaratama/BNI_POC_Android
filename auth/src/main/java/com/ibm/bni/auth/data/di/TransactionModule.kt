package com.ibm.bni.auth.data.di

import com.ibm.bni.auth.data.remote.api.TransactionApi
import com.ibm.bni.auth.data.remote.repository.TransactionRepositoryImpl
import com.ibm.bni.auth.domain.repository.TransactionRepository
import com.ibm.bni.auth.domain.usecase.GetCurrencyUseCase
import com.ibm.bni.auth.domain.usecase.getCurrency
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TransactionModule {

    @Provides
    fun provideGetCurrencyUseCase(transactionRepository: TransactionRepository) : GetCurrencyUseCase {
        return GetCurrencyUseCase {
            getCurrency(transactionRepository)
        }
    }

    @Provides
    fun provideTransactionApi(retrofit: Retrofit) : TransactionApi {
        return retrofit.create(TransactionApi::class.java)
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface BindModule {

        @Binds
        @Singleton
        fun bindPlanRepository(impl : TransactionRepositoryImpl) : TransactionRepository

    }

}