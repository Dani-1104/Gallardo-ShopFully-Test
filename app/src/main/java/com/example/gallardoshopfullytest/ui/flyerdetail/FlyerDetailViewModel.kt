package com.example.gallardoshopfullytest.ui.flyerdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gallardoshopfullytest.core.tracking.StreamFully
import com.example.gallardoshopfullytest.core.tracking.createFlyerSessionEvent

class FlyerDetailViewModel(
    private val streamFully: StreamFully
) : ViewModel() {
    fun onBackButtonClicked(sessionDuration: Long, flyerId: String, isFirstRead: Boolean) {
        streamFully.process(createFlyerSessionEvent(flyerId, sessionDuration, isFirstRead))
    }
}

class FlyerDetailViewModelFactory(
    private val streamFully: StreamFully
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return FlyerDetailViewModel(streamFully) as T
    }
}