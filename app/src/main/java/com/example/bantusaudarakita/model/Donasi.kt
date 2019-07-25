package com.example.bantusaudarakita.model

import com.google.gson.annotations.SerializedName

class Donasi {
    @SerializedName("id")
    lateinit var id: String
    @SerializedName("id_buat_donasi")
    lateinit var id_buat_donasi: String
    @SerializedName("nama")
    lateinit var nama: String
    @SerializedName("email")
    lateinit var email: String
    @SerializedName("komentar")
    lateinit var komentar: String
    @SerializedName("jumlah_donasi")
    lateinit var jumlah_donasi: String
    @SerializedName("buktitf")
    lateinit var buktitf: String
    @SerializedName("konfirmasi")
    lateinit var konfirmasi: String
    @SerializedName("created_at")
    lateinit var created_at: String

    constructor() {}

    constructor(
        id_buat_donasi: String,
        nama: String,
        email: String,
        komentar: String,
        jumlah_donasi: String,
        buktitf: String,
        konfirmasi: String,
        created_at: String
    ){
        this.id_buat_donasi=id_buat_donasi
        this.nama=nama
        this.email=email
        this.komentar=komentar
        this.jumlah_donasi=jumlah_donasi
        this.buktitf=buktitf
        this.komentar=komentar
        this.created_at=created_at
    }

}