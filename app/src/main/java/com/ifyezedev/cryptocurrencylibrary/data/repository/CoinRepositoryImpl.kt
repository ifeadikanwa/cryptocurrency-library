package com.ifyezedev.cryptocurrencylibrary.data.repository

import com.ifyezedev.cryptocurrencylibrary.data.remote.CoinPaprikaApi
import com.ifyezedev.cryptocurrencylibrary.data.remote.data_transfer_object.CoinDetailDto
import com.ifyezedev.cryptocurrencylibrary.data.remote.data_transfer_object.CoinDto
import com.ifyezedev.cryptocurrencylibrary.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi
    ) : CoinRepository {
    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }

}