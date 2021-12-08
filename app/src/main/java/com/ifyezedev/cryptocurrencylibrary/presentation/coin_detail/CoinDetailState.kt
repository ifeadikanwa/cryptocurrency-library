package com.ifyezedev.cryptocurrencylibrary.presentation.coin_detail

import com.ifyezedev.cryptocurrencylibrary.domain.model.Coin
import com.ifyezedev.cryptocurrencylibrary.domain.model.CoinDetail

data class CoinDetailState (
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""
)