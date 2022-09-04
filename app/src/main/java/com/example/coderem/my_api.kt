package com.example.coderem

import android.service.autofill.UserData
import retrofit2.http.GET

interface my_api {
    @GET("v1/codeforces")
    suspend fun getCfData(): MutableList<CodeData>
}