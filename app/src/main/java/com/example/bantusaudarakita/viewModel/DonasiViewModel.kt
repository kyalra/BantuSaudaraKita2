package com.example.bantusaudarakita.viewModel

import android.arch.lifecycle.ViewModel
import com.example.bantusaudarakita.services.Const
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DonasiViewModel:ViewModel() {
    var retrofit : Retrofit? = null
    fun postDoanasi(): Retrofit?{
        if (retrofit == null){
            retrofit = Retrofit.Builder().baseUrl(Const.base_url).addConverterFactory(GsonConverterFactory.create()).build()
        }
        return retrofit
    }

}