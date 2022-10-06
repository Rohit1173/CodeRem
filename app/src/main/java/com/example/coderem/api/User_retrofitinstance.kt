package com.example.coderem.api

import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL =
    "https://competitive-coding-api.herokuapp.com/api/"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val okhttp = okhttp3.OkHttpClient.Builder()
    .addInterceptor(OkHttpProfilerInterceptor())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .client(okhttp)
    .baseUrl(BASE_URL)
    .build()

object User_retrofitInstance {
    val api: User_api by lazy {
        retrofit.create(User_api::class.java)
    }
}
