package com.ifyezedev.cryptocurrencylibrary.data.remote.data_transfer_object

import com.google.gson.annotations.SerializedName
import com.ifyezedev.cryptocurrencylibrary.domain.model.Coin

data class CoinDto(
    val id: String,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("is_new")
    val isNew: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
)

//function that maps out Dto to the data class
// that we'll be using to display data to the user
fun CoinDto.toCoin(): Coin {
    return Coin(
        id = id,
        isActive = isActive,
        name = name,
        rank = rank,
        symbol = symbol
    )
}