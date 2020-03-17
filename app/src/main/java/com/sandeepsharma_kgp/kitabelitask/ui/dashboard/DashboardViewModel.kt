package com.sandeepsharma_kgp.kitabelitask.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sandeepsharma_kgp.kitabelitask.ItemDTO

class DashboardViewModel : ViewModel() {
    private val _selectedItem = MutableLiveData<ItemDTO>()
    val selectedItem : LiveData<ItemDTO>
        get() =_selectedItem

    fun setItem(itemDetail: ItemDTO) {
        _selectedItem.value = itemDetail
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text
}