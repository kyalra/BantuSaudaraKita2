package com.example.bantusaudarakita.services

import com.example.bantusaudarakita.model.DataDonasi
import com.example.bantusaudarakita.model.Donasi
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*
import kotlin.random.Random

interface ApiOnly {
    @GET("donasi")
    fun getBuatdonasi(): Call<List<DataDonasi>>
    @Multipart
    @POST("donasi")
    fun postDonasi(
        @Field("id") id_buat_donasi: String,
        @Field("nama") nama: String,
        @Field("email") email: String,
        @Field("komen") komentar: String,
        @Field("jumlah_donasi") jumlah_donasi: String,
        @Part gambar:MultipartBody.Part
    ):Call<Donasi>
}