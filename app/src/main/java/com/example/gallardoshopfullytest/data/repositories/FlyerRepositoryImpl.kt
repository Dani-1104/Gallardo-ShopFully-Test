package com.example.gallardoshopfullytest.data.repositories

import com.example.gallardoshopfullytest.data.model.Flyer
import com.example.gallardoshopfullytest.data.network.local.FlyerPreferences
import com.example.gallardoshopfullytest.data.network.remote.FlyerService

class FlyerRepositoryImpl(
    private val flyerService: FlyerService,
    private val flyerPreferences: FlyerPreferences
) : FlyerRepository {

    override suspend fun getFlyers(): List<Flyer> {
        var flyers: List<Flyer>
        try {
            flyers = flyerService.getFlyers()
            flyerPreferences.saveFlyers(flyers)
        } catch (e: Exception) {
            flyers = flyerPreferences.getFlyers()
        }
        return flyers
    }
}
