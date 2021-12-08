package com.ifyezedev.cryptocurrencylibrary.data.remote.data_transfer_object


import com.google.gson.annotations.SerializedName

data class LinksExtended(
    val stats: Stats,
    val type: String,
    val url: String
)