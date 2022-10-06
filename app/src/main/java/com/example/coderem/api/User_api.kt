package com.example.coderem.api

import com.example.coderem.CCdata
import retrofit2.http.GET
import retrofit2.http.Path

interface User_api {

    @GET("codechef/{path1}")
    suspend fun getuserCcData(
        @Path("path1") path1:String
    ): CCdata

//    @GET("codeforces/{path2}")
//    suspend fun getuserCfData(
//        @Path("path2") path2:String
//    ): MutableList<CodeData>
//
//    @GET("leetcode/{path3}")
//    suspend fun getuserLcData(
//        @Path("path3") path3:String
//    ): MutableList<CodeData>
}