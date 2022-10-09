package com.example.coderem

data class CCdata(
    val status:String,
    val rating:String,
    val stars:String,
    val highest_rating:String,
    val global_rank:String,
    val country_rank:String,
    val user_details: userDetails,
    val contest_ratings:List<ContestRating>
)
