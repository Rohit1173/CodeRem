package com.example.coderem

import com.squareup.moshi.Json

data class CfContest(
    val Contest:String,
    val Rank:String,
    val Solved:String,
    @Json(name = "Rating Change") val rating_change:String,
    @Json(name = "New Rating")val new_rating:String
)
