package br.com.fiap.startupQair.service;

import br.com.fiap.startupQair.model.ApiResponse
import br.com.fiap.startupQair.model.IndiceAr;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query

interface IndiceArService {

    //@GET("feed/here/?token=715edb9e453f76499eb715194bc3f14fe36760e9")
    //fun getIndiceArByLocal(): Call<ApiResponse>

    @GET("search/")
    fun geIndiceArByWord(
        @Query("token") token: String,
        @Query("keyword") word: String): Call<ApiResponse>
}