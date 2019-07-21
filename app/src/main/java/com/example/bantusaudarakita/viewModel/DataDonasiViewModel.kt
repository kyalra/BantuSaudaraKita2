package com.example.bantusaudarakita.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.bantusaudarakita.model.DataDonasi
import com.example.bantusaudarakita.services.ApiOnly
import com.example.bantusaudarakita.services.Const
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DataDonasiViewModel : ViewModel() {
    var listDonasi: MutableLiveData<List<DataDonasi>>? = null
    val dataDonasi: LiveData<List<DataDonasi>>
        get() {
            if (listDonasi == null) {
                listDonasi = MutableLiveData()
                loadDataDonasi()
            }
            return listDonasi!!
        }

    private fun loadDataDonasi() {
        val retrofit =
            Retrofit.Builder().baseUrl(Const.base_url).addConverterFactory(GsonConverterFactory.create()).build()
        val apiData = retrofit.create(ApiOnly::class.java)
        val getData = apiData.getBuatdonasi()
        getData.enqueue(object :Callback<List<DataDonasi>>{
            override fun onFailure(call: Call<List<DataDonasi>>, t: Throwable) {
                   TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<List<DataDonasi>>, response: Response<List<DataDonasi>>) {
                listDonasi!!.value=response.body()
            }
        })
    }
}