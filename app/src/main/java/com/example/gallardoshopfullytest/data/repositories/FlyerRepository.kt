package com.example.gallardoshopfullytest.data.repositories

import com.example.gallardoshopfullytest.data.model.Flyer

interface FlyerRepository {
    suspend fun getFlyers(): List<Flyer>
}