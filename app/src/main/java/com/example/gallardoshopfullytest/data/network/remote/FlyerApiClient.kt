package com.example.gallardoshopfullytest.data.network.remote

import com.example.gallardoshopfullytest.data.model.FlyerResponse
import retrofit2.Response
import retrofit2.http.GET

interface FlyerApiClient {
    @GET("v3/94da1ce3-3d3f-414c-8857-da813df3bb05")
    suspend fun getFlyers(): Response<FlyerResponse>
}