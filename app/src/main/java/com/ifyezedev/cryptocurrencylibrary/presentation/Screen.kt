package com.ifyezedev.cryptocurrencylibrary.presentation

//this class is used for our navigation
sealed class Screen(val route: String) {
    object CoinListScreen: Screen("coin_list_screen")
    object CoinDetailScreen: Screen("coin_detail_screen")
}
