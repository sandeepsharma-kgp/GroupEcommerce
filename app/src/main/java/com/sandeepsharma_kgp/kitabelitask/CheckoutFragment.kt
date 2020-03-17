package com.sandeepsharma_kgp.kitabelitask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sandeepsharma_kgp.kitabelitask.databinding.FragmentNotificationsBinding
import com.travijuu.numberpicker.library.Enums.ActionEnum
import com.travijuu.numberpicker.library.Interface.ValueChangedListener
import com.travijuu.numberpicker.library.Listener.DefaultValueChangedListener
import kotlinx.android.synthetic.main.purchase_item.*

class CheckoutFragment : Fragment() {

    private lateinit var checkoutViewModel: CheckoutViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        checkoutViewModel =
                ViewModelProviders.of(this).get(CheckoutViewModel::class.java)
        val binding = FragmentNotificationsBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = checkoutViewModel
        val quantityCount : Int
        val item : ItemDTO
        CheckoutFragmentArgs.fromBundle(arguments!!).apply {
            quantityCount = quantity.toInt()
            item = selectedItem
        }

        checkoutViewModel.setItem(item)
        checkoutViewModel.setQuantity(quantityCount)
//        binding.numberPicker.value = quantityCount


        binding.numberPicker.setValueChangedListener(object : ValueChangedListener {
            override fun valueChanged(value: Int, action: ActionEnum?) {
                checkoutViewModel.setQuantity(value)
            }
        })


        return binding.root
    }
}
