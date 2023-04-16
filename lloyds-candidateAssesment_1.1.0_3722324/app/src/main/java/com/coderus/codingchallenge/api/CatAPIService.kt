package com.coderus.codingchallenge.api

import com.coderus.codingchallenge.data.CatResponseItem
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.Executors

interface CatAPIService {
    @GET("api/cats")
     suspend fun getCatData(@Query("tags") tags: String = "cute"): Response<List<CatResponseItem>>
    companion object{
        operator fun invoke(): CatAPIService = Retrofit.Builder()
            .baseUrl("https://cataas.com/")
            .client(OkHttpClient.Builder().build())
            .callbackExecutor(Executors.newSingleThreadExecutor())
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder()
                        .add(KotlinJsonAdapterFactory())
                        .build()
                )
            )
            .build()
            .create(CatAPIService::class.java)
    }
}