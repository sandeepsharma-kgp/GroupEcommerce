package com.sandeepsharma_kgp.kitabelitask

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.sandeepsharma_kgp.kitabelitask.databinding.FragmentDashboardBinding
import com.smarteist.autoimageslider.IndicatorAnimations
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView


class ItemDetailFragment : Fragment() {

    private lateinit var itemDetailViewModel: ItemDetailViewModel
    lateinit var sliderView: SliderView
    private lateinit var adapter: SliderAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        itemDetailViewModel =
            ViewModelProviders.of(this).get(ItemDetailViewModel::class.java)
        val binding = FragmentDashboardBinding.inflate(inflater)
        binding.viewModel = itemDetailViewModel
        binding.lifecycleOwner = this
        val itemDetail = ItemDetailFragmentArgs.fromBundle(
            arguments!!
        ).selectedItem
        itemDetailViewModel.setItem(itemDetail)

        sliderView = binding.imageSlider
        adapter = this.context?.let {
            SliderAdapter(it)
        }!!
        sliderView.setSliderAdapter(adapter)
        sliderView.setIndicatorAnimation(IndicatorAnimations.THIN_WORM) //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT)
        sliderView.setIndicatorSelectedColor(Color.WHITE)
        sliderView.setIndicatorUnselectedColor(Color.GRAY)
        sliderView.setScrollTimeInSec(3)

        itemDetailViewModel.selectedItem.observe(viewLifecycleOwner, Observer {
            it.let {
                adapter.submitList(it.images)
            }
        })

        binding.buyButton.setOnClickListener {
            this.findNavController().navigate(
                ItemDetailFragmentDirections.actionToCheckout(
                    itemDetail,
                    binding.numberPicker.value.toString()
                )
            )
        }

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            view?.findNavController()?.navigate(R.id.navigation_home)
        }

        itemDetailViewModel.itemUrl.observe(viewLifecycleOwner, Observer {
            it.let {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.setType("text/plain")
                shareIntent.putExtra(Intent.EXTRA_TEXT, it)
                Toast.makeText(this.context, it, Toast.LENGTH_SHORT).show()
                activity?.startActivity(Intent.createChooser(shareIntent, "Share via"))
            }
        })

        return binding.root
    }
}
