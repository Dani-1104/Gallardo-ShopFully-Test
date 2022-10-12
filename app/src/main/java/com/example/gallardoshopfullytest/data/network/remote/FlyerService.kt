package com.example.gallardoshopfullytest.data.network.remote

import com.example.gallardoshopfullytest.core.RetrofitHelper
import com.example.gallardoshopfullytest.data.model.Flyer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FlyerService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getFlyers(): List<Flyer> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(FlyerApiClient::class.java).getFlyers()
            response.body()?.data ?: emptyList()
        }
    }
}