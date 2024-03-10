package br.com.fiap.startupQair.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactoryIndiceAr {

    private val URL = "https://api.waqi.info/"

    private val retrofitFactory = Retrofit
        .Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getIndiceArService(): IndiceArService {
        return retrofitFactory.create(IndiceArService::class.java)
    }
}