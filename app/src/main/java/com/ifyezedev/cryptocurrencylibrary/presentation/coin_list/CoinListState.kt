package com.ifyezedev.cryptocurrencylibrary.presentation.coin_list

import com.ifyezedev.cryptocurrencylibrary.domain.model.Coin

data class CoinListState (
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)