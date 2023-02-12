package com.example.br_search_jetpack

import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface ApiInterface {

    @GET("todos")
    suspend fun getUserData(): List<UserResponse>
}