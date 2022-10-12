package com.example.gallardoshopfullytest.data.model

import com.google.gson.annotations.SerializedName

data class FlyerResponse(
    @SerializedName("data") val data: List<Flyer>
)

data class Flyer(
    @SerializedName("id") val id: String,
    @SerializedName("retailer_id") val retailerId: String,
    @SerializedName("title") val title: String,
    var timesRead: Int = 0
)