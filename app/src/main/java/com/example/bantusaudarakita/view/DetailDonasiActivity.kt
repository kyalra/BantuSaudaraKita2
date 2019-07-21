package com.example.bantusaudarakita.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.bantusaudarakita.R
import com.example.bantusaudarakita.services.Const
import kotlinx.android.synthetic.main.daftar_donasi.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class DetailDonasiActivity : AppCompatActivity() {
    val retrofit: Retrofit? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.donasi_activity)
        val judul = intent.getStringExtra("judul")
        val keterangan = intent.getStringExtra("keterangan")
        val jumlah_tercapai = intent.getStringExtra("jumlah_tercapai")
        val jumlah = intent.getStringExtra("jumlah")
        val foto = intent.getStringExtra("foto")
        val urlpoto = "http://10.10.20.24:8000/${foto}"

        Judul.text = judul
        Keterangan.text=keterangan
        donasiTercapai.text=jumlah_tercapai
        TotalDonasi.text=judul
        Glide.with(this).load(urlpoto).into(Foto)
val interceptor=HttpLoggingInterceptor()
        interceptor.level=HttpLoggingInterceptor.Level.BODY
        val client=OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(100,TimeUnit.SECONDS)
            .readTimeout(100,TimeUnit.SECONDS)
            .build()
        val retrofit=Retrofit.Builder().client(client).baseUrl(Const.base_url).addConverterFactory(GsonConverterFactory.create()).build()
    }
}
