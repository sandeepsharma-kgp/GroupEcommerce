package com.sandeepsharma_kgp.kitabelitask

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private const val BASE_URL =
    "http://ec2-3-7-0-164.ap-south-1.compute.amazonaws.com:8080/api/v1/group/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()


interface DataSource {
    @GET("test/")
    fun getData() : Deferred<Result>
}

object DataSourceApi {
    val retrofitService : DataSource by lazy {
        retrofit.create(DataSource::class.java)
    }
}