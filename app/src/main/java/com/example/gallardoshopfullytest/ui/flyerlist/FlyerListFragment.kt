package com.example.gallardoshopfullytest.ui.flyerlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gallardoshopfullytest.R
import com.example.gallardoshopfullytest.databinding.FragmentFlyerListBinding
import com.example.gallardoshopfullytest.ui.navigation.Navigator
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class FlyerListFragment : Fragment(), KodeinAware {

    private val viewModelFactory: FlyerListViewModelFactory by instance()
    private val navigator: Navigator by instance()

    private lateinit var viewModel: FlyerListViewModel
    private lateinit var flyerListAdapter: FlyerListAdapter

    override val kodein by closestKodein()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_flyer_list, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[FlyerListViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        flyerListAdapter = FlyerListAdapter { flyer ->
            ++flyer.timesRead
            val position = viewModel.flyers.value?.indexOf(flyer)
            position?.let {
                flyerListAdapter.notifyItemChanged(it)
                viewModel.trackFlyerOpen(flyer, it)
            }
            navigator.goToFlyerDetail(this, flyer)
        }

        val binding = FragmentFlyerListBinding.bind(view).apply {
            flyers.layoutManager = GridLayoutManager(context, 2)
            flyers.adapter = flyerListAdapter
        }
        binding.toolbar.toggle.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onToggleClicked(isChecked)
        }
        viewModel.loading.observe(viewLifecycleOwner) { isVisible ->
            binding.progressView.visibility = if (isVisible) View.VISIBLE else View.GONE
        }
        viewModel.flyers.observe(viewLifecycleOwner) { flyers ->
            flyerListAdapter.replace(flyers)
        }
        viewModel.toggleActive.observe(viewLifecycleOwner) { isToggleActive ->
            viewModel.flyers.value?.filter { flyer ->
                !(isToggleActive && flyer.timesRead == 0)
            }?.let { flyerListAdapter.replace(it) }
        }
    }

}