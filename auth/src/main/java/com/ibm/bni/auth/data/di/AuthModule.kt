package com.ibm.bni.auth.data.di

import com.ibm.bni.auth.data.remote.api.AuthApi
import com.ibm.bni.auth.data.remote.api.BeneficiaryApi
import com.ibm.bni.auth.data.remote.repository.AuthRepositoryImpl
import com.ibm.bni.auth.data.remote.repository.BeneficiaryRepositoryImpl
import com.ibm.bni.auth.domain.repository.AuthRepository
import com.ibm.bni.auth.domain.repository.BeneficiaryRepository
import com.ibm.bni.auth.domain.usecase.GenerateOtpUseCase
import com.ibm.bni.auth.domain.usecase.GetReceiverUseCase
import com.ibm.bni.auth.domain.usecase.generateOtp
import com.ibm.bni.auth.domain.usecase.getReceivers
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
    fun provideGenerateOtpUseCase(authRepository: AuthRepository) : GenerateOtpUseCase {
        return GenerateOtpUseCase {
            generateOtp(authRepository)
        }
    }

    @Provides
    fun provideBeneficiaryUseCase(beneficiaryRepository: BeneficiaryRepository) : GetReceiverUseCase {
        return GetReceiverUseCase {
            getReceivers(beneficiaryRepository)
        }
    }

    @Provides
    fun provideAuthApi(retrofit: Retrofit) : AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

    @Provides
    fun provideBeneficiaryApi(retrofit: Retrofit) : BeneficiaryApi {
        return retrofit.create(BeneficiaryApi::class.java)
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface BindModule {

        @Binds
        @Singleton
        fun bindPlanRepository(impl : AuthRepositoryImpl) : AuthRepository

        @Binds
        @Singleton
        fun bindBeneRepository(impl : BeneficiaryRepositoryImpl) : BeneficiaryRepository

    }
}