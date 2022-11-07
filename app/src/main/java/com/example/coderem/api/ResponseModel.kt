package com.example.coderem.api

data class ResponseModel<T, E>(
    var successModel: T,
    var errorModel: E
)