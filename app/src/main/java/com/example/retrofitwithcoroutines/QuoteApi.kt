package com.example.retrofitwithcoroutines

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteApi {

   /* @GET("/v1/quote")
    suspend fun getQuotes(@Query("/50") value : Int) :Response<QuoteList>*/

    @GET("v1/quote/50")
    suspend fun getQuote(): Response<QuoteList>
}