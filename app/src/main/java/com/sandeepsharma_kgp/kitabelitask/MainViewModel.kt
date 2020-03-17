package com.sandeepsharma_kgp.kitabelitask

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
//    init {
//        getData()
//    }
//    private lateinit var viewModelJob : Job
//    private val _data = MutableLiveData<List<ResponseModel>>()
//    val data: LiveData<List<ResponseModel>>
//        get() = _data
//    private fun getData() {
//        viewModelJob = CoroutineScope(Job() + Dispatchers.Main).launch {
//            var data =DataSourceApi.retrofitService.getData()
//            try {
//                var listData = data.await()
//                _data.value = listData.payload
//                _data.value = listData.payload
//            } catch (t : Throwable) {
//                Log.e("ERROR", t.toString())
//            }
//        }
//    }

}