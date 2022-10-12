package com.example.gallardoshopfullytest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein

class MainActivity : AppCompatActivity(), KodeinAware {

    override val kodein by closestKodein()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findNavController(R.id.nav_host_fragment).setGraph(R.navigation.flyers_nav_graph)
    }
}