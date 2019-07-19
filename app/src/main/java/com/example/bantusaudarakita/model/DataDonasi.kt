package com.example.bantusaudarakita.model

import com.google.gson.annotations.SerializedName

class DataDonasi {

    @SerializedName("id")
    lateinit var id: String
    @SerializedName("users_id")
    lateinit var users_id: String
    @SerializedName("judul")
    lateinit var judul: String
    @SerializedName("keterangan")
    lateinit var keterangan: String
    @SerializedName("gambar")
    lateinit var gambar: String
    @SerializedName("jumlah")
    lateinit var jumlah: String
    @SerializedName("norek")
    lateinit var norek: String
    @SerializedName("jumlah_terkumpul")
    lateinit var jumlah_terkumpul: String

    constructor() {}

    constructor(
        judul: String,
        keterangan: String,
        gambar: String,
        jumlah: String,
        norek: String,
        jumlah_terkumpul: String
    ) {
        this.judul = judul
        this.keterangan = keterangan
        this.gambar = gambar
        this.jumlah = jumlah
        this.norek = norek
        this.jumlah_terkumpul = jumlah_terkumpul
    }

}