package com.coderus.codingchallenge.repository

import com.coderus.codingchallenge.api.CatAPIService
import com.coderus.codingchallenge.api.safeApiRequest

class CatRepository(
    private val api: CatAPIService
) : safeApiRequest(){
   suspend fun getCat() = apiRequest { api.getCatData() }
}