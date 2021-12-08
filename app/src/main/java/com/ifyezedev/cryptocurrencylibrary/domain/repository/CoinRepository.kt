package com.ifyezedev.cryptocurrencylibrary.domain.repository

import com.ifyezedev.cryptocurrencylibrary.data.remote.data_transfer_object.CoinDetailDto
import com.ifyezedev.cryptocurrencylibrary.data.remote.data_transfer_object.CoinDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String) : CoinDetailDto
}