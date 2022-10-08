package com.example.coderem.api

import com.example.coderem.CodeData
import retrofit2.http.GET

interface My_api {
    @GET("v1/all")
    suspend fun getCodeData(): MutableList<CodeData>
}