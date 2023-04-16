package com.coderus.codingchallenge.api

import android.util.Log
import retrofit2.Response
import java.io.IOException

abstract class safeApiRequest {
    suspend fun<T: Any> apiRequest(call: suspend () -> Response<T>) : T {
        val response = call.invoke()
        if(response.isSuccessful){
            return response.body()!!
        }else{
            throw ApiException(response.code().toString())
        }

    }
}
class ApiException(message: String): IOException(message)
