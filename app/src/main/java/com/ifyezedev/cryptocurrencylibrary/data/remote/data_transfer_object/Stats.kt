package com.ifyezedev.cryptocurrencylibrary.data.remote.data_transfer_object


import com.google.gson.annotations.SerializedName

data class Stats(
    val contributors: Int,
    val followers: Int,
    val stars: Int,
    val subscribers: Int
)