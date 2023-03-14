package com.ibm.bni.auth.data.di

import com.ibm.bni.auth.data.remote.repository.PreLoginRepositoryImpl
import com.ibm.bni.auth.domain.repository.PreLoginRepository
import com.ibm.bni.auth.domain.usecase.*
import com.ibm.bni.auth.data.remote.api.PreLoginApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object PreLoginModule {
    @Provides
    fun provideLoginUseCase(preLoginRepository: PreLoginRepository): LoginUseCase {
        return LoginUseCase {
            login(preLoginRepository)
        }
    }

    @Provides
    fun provideGetRegisteredUserUseCase(preLoginRepository: PreLoginRepository): GetRegisteredUserUseCase{
        return GetRegisteredUserUseCase {
            getRegisteredUser(preLoginRepository)
        }
    }

    @Provides
    fun provideNewAndHotDealUseCase(preLoginRepository: PreLoginRepository): GetNewAndHotDealUseCase {
        return GetNewAndHotDealUseCase{
            getNewAndHotDeal(preLoginRepository)
        }
    }

    @Provides
    fun provideSeeBalanceUseCase(preLoginRepository: PreLoginRepository): SeeBalanceUseCase
    {
        return SeeBalanceUseCase {
            seeBalance(preLoginRepository)
        }
    }

    @Provides
    fun provideLoginPagePref(preLoginRepository: PreLoginRepository):GetLoginPageUseCase{
        return GetLoginPageUseCase{
            getLoginPagePref(preLoginRepository)
        }
    }

    @Provides
    fun provideUserData(preLoginRepository: PreLoginRepository):GetUserDataUseCase{
        return GetUserDataUseCase {
            getUserData(preLoginRepository)
        }
    }

    @Provides
    fun providePreLoginApi(retrofit: Retrofit): PreLoginApi {
        return retrofit.create(PreLoginApi::class.java)
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface BindModule{
        @Binds
        @Singleton
        fun bindPlanRepository(impl: PreLoginRepositoryImpl):PreLoginRepository
    }
}