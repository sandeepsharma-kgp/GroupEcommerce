package com.sandeepsharma_kgp.kitabelitask.checkout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sandeepsharma_kgp.kitabelitask.ItemDTO

class CheckoutViewModel : ViewModel() {
    private val _selectedItem = MutableLiveData<ItemDTO>()
    val selectedItem: LiveData<ItemDTO>
        get() = _selectedItem

    private val _quantityCount = MutableLiveData<Int>()
    val quantityCount: LiveData<Int>
        get() = _quantityCount

    fun setItem(item: ItemDTO) {
        _selectedItem.value = item
    }

    fun setQuantity(quantityCount: Int) {
        _quantityCount.value = quantityCount
    }
}