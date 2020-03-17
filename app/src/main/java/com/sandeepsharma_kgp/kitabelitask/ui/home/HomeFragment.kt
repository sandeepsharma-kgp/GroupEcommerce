package com.sandeepsharma_kgp.kitabelitask.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.sandeepsharma_kgp.kitabelitask.ItemListAdapter
import com.sandeepsharma_kgp.kitabelitask.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater)
        binding.lifecycleOwner = this
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        binding.viewModel = homeViewModel
        binding.itemList.adapter = ItemListAdapter(ItemListAdapter.OnClickListener {
            homeViewModel.displayDetail(it)
        })
        homeViewModel.navigateToSelectedItem.observe(viewLifecycleOwner, Observer {
            it?.let {
                this.findNavController().navigate(
                    HomeFragmentDirections.actionNavigationHomeToNavigationDashboard(it)
                )
            }
        })

        return binding.root
    }
}
