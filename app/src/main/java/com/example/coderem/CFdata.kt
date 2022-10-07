package com.example.coderem

import com.squareup.moshi.Json

data class CFdata(
      val status:String,
      val username:String,
      val platform:String,
      val rating:String,
      @Json(name = "max rating") val max_rating:String,
      val rank:String,
      @Json(name = "max rank")val max_rank:String
)
