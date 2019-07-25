package com.example.bantusaudarakita.services

import com.example.bantusaudarakita.model.DataDonasi
import com.example.bantusaudarakita.model.Donasi
import com.example.bantusaudarakita.model.Gmbar
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*
import kotlin.random.Random

interface ApiOnly {
    @GET("donasi")
    fun getBuatdonasi(): Call<List<DataDonasi>>

    @POST("donasi")
    @FormUrlEncoded
    fun postDonasi(
        @Field("id_buat_donasi") id_buat_donasi: String,
        @Field("nama") nama: String,
        @Field("email") email: String,
        @Field("komen") komentar: String,
        @Field("jumlah_donasi") jumlah_donasi: String,
        @Field("buktitf")buktitf: String
    ):Call<Donasi>
    @Multipart
    @POST("upload")
    fun upload(
        @Part gambar : MultipartBody.Part
    ):Call<Gmbar>
}