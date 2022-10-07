package com.example.coderem.api

import com.example.coderem.CCdata
import com.example.coderem.CFdata
import com.example.coderem.LCdata
import com.example.coderem.ProfileStatus
import retrofit2.http.GET
import retrofit2.http.Path

interface User_api {

    @GET("codechef/{path1}")
    suspend fun getuserCcStatus(
        @Path("path1") path1:String
    ): ProfileStatus

    @GET("codeforces/{path2}")
    suspend fun getuserCfStatus(
        @Path("path2") path2:String
    ): ProfileStatus

    @GET("leetcode/{path3}")
    suspend fun getuserLcStatus(
        @Path("path3") path3:String
    ): ProfileStatus

    @GET("codechef/{path1}")
    suspend fun getuserCcData(
        @Path("path1") path1:String
    ): CCdata

    @GET("codeforces/{path2}")
    suspend fun getuserCfData(
        @Path("path2") path2:String
    ): CFdata

    @GET("leetcode/{path3}")
    suspend fun getuserLcData(
        @Path("path3") path3:String
    ): LCdata

}