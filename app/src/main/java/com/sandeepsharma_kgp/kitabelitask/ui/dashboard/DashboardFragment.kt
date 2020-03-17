package com.sandeepsharma_kgp.kitabelitask.ui.dashboard

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.sandeepsharma_kgp.kitabelitask.R
import com.sandeepsharma_kgp.kitabelitask.databinding.FragmentDashboardBinding
import com.smarteist.autoimageslider.IndicatorAnimations
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView


class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    lateinit var sliderView: SliderView
    private lateinit var adapter: SliderAdapter
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        val binding = FragmentDashboardBinding.inflate(inflater)
        binding.viewModel = dashboardViewModel
        binding.lifecycleOwner = this
        val itemDetail = DashboardFragmentArgs.fromBundle(arguments!!).selectedItem
        dashboardViewModel.setItem(itemDetail)

        sliderView = binding.imageSlider
        adapter = this.context?.let { SliderAdapter(it) }!!
        sliderView.setSliderAdapter(adapter)
        sliderView.setIndicatorAnimation(IndicatorAnimations.THIN_WORM) //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT)
        sliderView.setIndicatorSelectedColor(Color.WHITE)
        sliderView.setIndicatorUnselectedColor(Color.GRAY)
        sliderView.setScrollTimeInSec(3)

        dashboardViewModel.selectedItem.observe(viewLifecycleOwner, Observer {
            it.let {
                adapter.submitList(it.images)
            }
        })


        requireActivity().onBackPressedDispatcher.addCallback(this) {
            view?.findNavController()?.navigate(R.id.navigation_home)
        }

        dashboardViewModel.itemUrl.observe(viewLifecycleOwner, Observer {
            it.let {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.putExtra(Intent.EXTRA_TEXT, it)
                activity?.startActivity(Intent.createChooser(shareIntent, "Share via"))
            }
        })

        return binding.root
    }
}
