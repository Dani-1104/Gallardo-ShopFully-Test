package com.example.gallardoshopfullytest.ui.flyerlist

import android.util.Log
import androidx.lifecycle.*
import com.example.gallardoshopfullytest.core.tracking.StreamFully
import com.example.gallardoshopfullytest.core.tracking.createFlyerOpenEvent
import com.example.gallardoshopfullytest.data.model.Flyer
import com.example.gallardoshopfullytest.data.repositories.FlyerRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch

class FlyerListViewModel(
    private val flyerRepository: FlyerRepository,
    private val streamFully: StreamFully
) : ViewModel() {
    private val _toggleActive = MutableLiveData(false)
    val toggleActive: LiveData<Boolean> get() = _toggleActive

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> get() = _loading

    private val _flyers = MutableLiveData<List<Flyer>>(emptyList())
    val flyers: LiveData<List<Flyer>> get() = _flyers

    init {
        viewModelScope.launch {
            _loading.value = true
            try {
                _flyers.value = flyerRepository.getFlyers()
            } catch (ce: CancellationException) {
                throw ce // Needed for coroutine scope cancellation
            } catch (e: Exception) {
                Log.d("getFlyers error", "error")
            }
            _loading.value = false
        }
    }

    fun onToggleClicked(isChecked: Boolean) {
        _toggleActive.value = isChecked
    }

    fun trackFlyerOpen(flyer: Flyer, position: Int) {
        val streamFullyEvent = createFlyerOpenEvent(flyer, position)
        streamFully.process(streamFullyEvent)
    }
}

class FlyerListViewModelFactory(
    private val flyerRepository: FlyerRepository,
    private val streamFully: StreamFully
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return FlyerListViewModel(flyerRepository, streamFully) as T
    }
}