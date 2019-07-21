package com.example.bantusaudarakita.services

import com.example.bantusaudarakita.model.DataDonasi
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiOnly {
    @GET("buat_donasi")
    fun getBuatdonasi(): Call<List<DataDonasi>>

    @POST("donatur")
    @FormUrlEncoded
    fun postDonasi(
        @Field("id_buat_donasi") id_buat_donasi: String,
        @Field("nama") nama: String,
        @Field("email") email: String,
        @Field("komentar") komentar: String,
        @Field("jumlah_donasi") jumlah_donasi: String,
        @Field("buktitf") buktitf: String
    )
}