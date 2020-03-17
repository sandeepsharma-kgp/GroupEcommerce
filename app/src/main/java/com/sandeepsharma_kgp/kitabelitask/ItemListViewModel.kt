package com.sandeepsharma_kgp.kitabelitask

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.sandeepsharma_kgp.kitabelitask.DataSourceApi
import com.sandeepsharma_kgp.kitabelitask.ItemDTO
import com.sandeepsharma_kgp.kitabelitask.MainViewModel
import com.sandeepsharma_kgp.kitabelitask.ResponseModel
import com.sandeepsharma_kgp.kitabelitask.Result
import kotlinx.coroutines.*

class ItemListViewModel : ViewModel() {


    private val _navigateToSelectedItem= MutableLiveData<ItemDTO>()
    val navigateToSelectedItem : LiveData<ItemDTO>
        get() = _navigateToSelectedItem
    init {
        getData()
    }
    private lateinit var viewModelJob : Job
    private val _data = MutableLiveData<List<ResponseModel>>()
    val data: LiveData<List<ResponseModel>>
        get() = _data
    private fun getData() {
        Log.e("ERROR", "FETCHING DATA...")
        viewModelJob = CoroutineScope(Job() + Dispatchers.Main).launch {
            var data = DataSourceApi.retrofitService.getData()
            try {
                Log.e("ERROR", "FETCHING DATA.......")
                var listData = data.await()
                _data.value = listData.payload
                Log.e("ERROR", "DATA.......")
            } catch (t : Throwable) {
                Log.e("ERROR", t.toString())
            }
        }

    }

    fun displayDetail(itemDetail: ItemDTO) {
        Log.e("ERROR", "DISPLAY.......")
        _navigateToSelectedItem.value = itemDetail
    }
}