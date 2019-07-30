package com.example.bantusaudarakita.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.util.Log.w
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.bantusaudarakita.R
import com.example.bantusaudarakita.model.DataDonasi
import com.example.bantusaudarakita.view.DetailDonasiActivity
import kotlinx.android.synthetic.main.daftar_donasi.view.*
import kotlinx.android.synthetic.main.donasi.view.*

class ListDonasiAdapter : RecyclerView.Adapter<ListDonasiAdapter.ListDonasiViewHolder> {
    lateinit var listDonasi: List<DataDonasi>
    lateinit var mContext: Context
    private var PICK_IMAGE_REQUEST = 71

    constructor() {

    }

    constructor(mContext: Context, listDonasi: List<DataDonasi>) {
        this.listDonasi = listDonasi
        this.mContext = mContext
    }

    override fun getItemCount(): Int {
        return listDonasi.size
    }

    override fun onBindViewHolder(p0: ListDonasiViewHolder, p1: Int) {
        val donasi = listDonasi[p1]
        val urlphoto = "https://bantusodarakita.rdlvindonesia.com//img/donasi/${donasi.gambar}"
        w("tag", "tes$urlphoto")
        Glide.with(mContext).load(urlphoto).apply(RequestOptions().placeholder(R.drawable.user)).into(p0.gambar)
        p0.judul.text=donasi.judul
        p0.keterangan.text=donasi.keterangan
        p0.donasi_tercapai.text=donasi.jumlah_terkumpul
        p0.total_donasi.text=donasi.jumlah

    }



    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ListDonasiViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.daftar_donasi, p0, false)
        view.btndonasi.setOnClickListener {
            val donasi = listDonasi[p1]
                val intent = Intent(mContext,DetailDonasiActivity::class.java)
            intent.putExtra("id", donasi.id)
            intent.putExtra("judul", donasi.judul)
            intent.putExtra("keterangan", donasi.keterangan)
            intent.putExtra("gambar", donasi.gambar)
            intent.putExtra("jumlah", donasi.jumlah)
            intent.putExtra("jumlah_terkumpul", donasi.jumlah_terkumpul)
                mContext.startActivity(intent)
        }



        return ListDonasiViewHolder(view)
    }


    class ListDonasiViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        var judul: TextView
        var keterangan: TextView
        var donasi_tercapai: TextView
        var total_donasi: TextView
        var gambar: ImageView

        init {
            judul = item.findViewById(R.id.Judul)
            keterangan = item.findViewById(R.id.Keterangan)
            donasi_tercapai = item.findViewById(R.id.donasiTercapai)
            total_donasi = item.findViewById(R.id.TotalDonasi)
            gambar = item.findViewById(R.id.Foto)
        }


    }
}