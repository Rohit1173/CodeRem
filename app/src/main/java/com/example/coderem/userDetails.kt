package com.example.coderem

import com.squareup.moshi.Json

data class userDetails(
    val name:String,
    val username:String,
    val country:String,
    @Json(name="student/professional") val student_OR_professional:String,
    val institution:String,
    @Json(name="codechef pro plan") val codechef_pro_plan:String
)
