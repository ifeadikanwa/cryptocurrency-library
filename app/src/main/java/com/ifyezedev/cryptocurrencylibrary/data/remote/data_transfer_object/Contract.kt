package com.ifyezedev.cryptocurrencylibrary.data.remote.data_transfer_object


import com.google.gson.annotations.SerializedName

data class Contract(
    val contract: String,
    val platform: String,
    val type: String
)