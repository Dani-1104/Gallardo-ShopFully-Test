package com.example.gallardoshopfullytest.ui.navigation

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.gallardoshopfullytest.R
import com.example.gallardoshopfullytest.data.model.Flyer

class Navigator {
    companion object {
        const val BUNDLE_FLYER_TITLE = "flyerTitle"
        const val BUNDLE_FLYER_ID = "flyerId"
        const val BUNDLE_IS_FIRST_READ = "isFirstRead"
    }

    fun goToFlyerDetail(fragment: Fragment, flyer: Flyer) {
        val bundle = bundleOf(
            BUNDLE_FLYER_TITLE to flyer.title,
            BUNDLE_FLYER_ID to flyer.id,
            BUNDLE_IS_FIRST_READ to (flyer.timesRead == 1)
        )
        fragment.findNavController().navigate(
            R.id.navigate_from_flyer_list_to_detail,
            bundle
        )
    }
}