package com.cursokotlin.imageslist.Service

import com.cursokotlin.imageslist.Model.ImageRandom
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun getRandomImage(@Url url: String): Response<List<ImageRandom>>
}