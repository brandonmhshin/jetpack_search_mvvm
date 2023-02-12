package com.example.br_search_jetpack

import dagger.hilt.android.scopes.ActivityScoped
import java.lang.Exception
import javax.inject.Inject


@ActivityScoped
class UserRespository @Inject constructor(
    private val apiInterface: ApiInterface
) {

    suspend fun getUserResponse(): Resource<List<UserResponse>> {
        val response = try {
            apiInterface.getUserData()
        } catch (e: Exception) {
            return Resource.Error("An unknown error occured: ${e.localizedMessage}")
        }

        return Resource.Success(response)
    }
}