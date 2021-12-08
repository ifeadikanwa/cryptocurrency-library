package com.ifyezedev.cryptocurrencylibrary.di

import com.ifyezedev.cryptocurrencylibrary.common.Constants
import com.ifyezedev.cryptocurrencylibrary.data.remote.CoinPaprikaApi
import com.ifyezedev.cryptocurrencylibrary.data.repository.CoinRepositoryImpl
import com.ifyezedev.cryptocurrencylibrary.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesPaprikaApi() : CoinPaprikaApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinPaprikaApi::class.java)
    }

    @Provides
    @Singleton
    fun providesCoinRepository(api: CoinPaprikaApi): CoinRepository {
        return CoinRepositoryImpl(api)
    }
}