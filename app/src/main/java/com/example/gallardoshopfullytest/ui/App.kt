package com.example.gallardoshopfullytest.ui

import android.app.Application
import com.example.gallardoshopfullytest.BuildConfig
import com.example.gallardoshopfullytest.core.tracking.StreamFully
import com.example.gallardoshopfullytest.data.network.local.FlyerPreferences
import com.example.gallardoshopfullytest.data.network.remote.FlyerService
import com.example.gallardoshopfullytest.data.repositories.FlyerRepository
import com.example.gallardoshopfullytest.data.repositories.FlyerRepositoryImpl
import com.example.gallardoshopfullytest.ui.flyerdetail.FlyerDetailViewModelFactory
import com.example.gallardoshopfullytest.ui.flyerlist.FlyerListViewModelFactory
import com.example.gallardoshopfullytest.ui.navigation.Navigator
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class App : Application(), KodeinAware {

    override val kodein by Kodein.lazy {
        import(androidXModule(this@App))
        bind<Navigator>() with singleton { Navigator() }
        bind<StreamFully>() with singleton {
            StreamFully(
                BuildConfig.APPLICATION_ID,
                BuildConfig.VERSION_NAME
            )
        }
        bind<FlyerService>() with singleton { FlyerService() }
        bind<FlyerPreferences>() with singleton { FlyerPreferences(this@App) }
        bind<FlyerRepository>() with singleton { FlyerRepositoryImpl(instance(), instance()) }
        bind() from provider { FlyerListViewModelFactory(instance(), instance()) }
        bind() from provider { FlyerDetailViewModelFactory(instance()) }
    }
}