package com.example.gallardoshopfullytest.ui.flyerdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.gallardoshopfullytest.R
import com.example.gallardoshopfullytest.databinding.FragmentFlyerDetailBinding
import com.example.gallardoshopfullytest.ui.core.extension.loadFlyerImage
import com.example.gallardoshopfullytest.ui.navigation.Navigator
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class FlyerDetailFragment : Fragment(), KodeinAware {
    private val viewModelFactory: FlyerDetailViewModelFactory by instance()
    private var openTime: Long = 0

    private lateinit var viewModel: FlyerDetailViewModel

    override val kodein by closestKodein()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_flyer_detail, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[FlyerDetailViewModel::class.java]
        openTime = System.currentTimeMillis()
    }

    override fun onPause() {
        val isFirstRead = arguments?.getBoolean(Navigator.BUNDLE_IS_FIRST_READ) ?: false
        val flyerId = arguments?.getString(Navigator.BUNDLE_FLYER_ID) ?: ""
        viewModel.onBackButtonClicked(
            System.currentTimeMillis() - openTime,
            flyerId,
            isFirstRead
        )
        super.onPause()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentFlyerDetailBinding.bind(view)

        val flyerTitle = arguments?.getString(Navigator.BUNDLE_FLYER_TITLE)
        val flyerId = arguments?.getString(Navigator.BUNDLE_FLYER_ID) ?: ""
        binding.toolbar.title.text = flyerTitle
        binding.image.loadFlyerImage(flyerId)

        binding.toolbar.backButton.setOnClickListener {
            activity?.onBackPressed()
        }
    }
}