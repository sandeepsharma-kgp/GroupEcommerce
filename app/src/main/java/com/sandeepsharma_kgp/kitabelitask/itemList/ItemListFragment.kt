package com.sandeepsharma_kgp.kitabelitask.itemList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.sandeepsharma_kgp.kitabelitask.databinding.FragmentItemListBinding

class ItemListFragment : Fragment() {

    private lateinit var itemListViewModel: ItemListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentItemListBinding.inflate(inflater)
        binding.lifecycleOwner = this
        itemListViewModel =
            ViewModelProviders.of(this).get(ItemListViewModel::class.java)
        binding.viewModel = itemListViewModel
        binding.itemList.adapter =
            ItemListAdapter(
                ItemListAdapter.OnClickListener {
                    itemListViewModel.displayDetail(it)
                })
        itemListViewModel.navigateToSelectedItem.observe(viewLifecycleOwner, Observer {
            it?.let {
                this.findNavController().navigate(
                    ItemListFragmentDirections.actionToItemDetail(it)
                )
            }
        })

        return binding.root
    }
}
