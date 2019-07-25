package com.example.bantusaudarakita.view

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log.*
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.bantusaudarakita.R

import com.example.bantusaudarakita.model.Donasi
import com.example.bantusaudarakita.model.Gmbar
import com.example.bantusaudarakita.services.ApiOnly
import com.example.bantusaudarakita.services.Const
import com.squareup.picasso.Picasso
import com.vincent.filepicker.Constant
import com.vincent.filepicker.Constant.REQUEST_CODE_PICK_IMAGE
import com.vincent.filepicker.Constant.RESULT_PICK_IMAGE
import com.vincent.filepicker.activity.ImagePickActivity
import com.vincent.filepicker.filter.entity.ImageFile
import kotlinx.android.synthetic.main.daftar_donasi.*
import kotlinx.android.synthetic.main.donasi.*
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import pub.devrel.easypermissions.EasyPermissions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.io.File
import java.util.concurrent.TimeUnit


class DetailDonasiActivity : AppCompatActivity() {
    val retrofit: Retrofit? = null
    private var mImageView:ImageView?=null
    private var uri:Uri?=null
    private var gambar: MultipartBody.Part?=null
    private var id: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.donasi)

        val nama = findViewById<TextView>(R.id.nama)
//        gambar=findViewById(R.id.buktitf1)


        id = intent.getStringExtra("id")
        e("tag", "id $id")
        buktitf.setOnClickListener {
            if(EasyPermissions.hasPermissions(this,android.Manifest.permission.READ_EXTERNAL_STORAGE)){
                val i = Intent(this, ImagePickActivity::class.java)
                i.putExtra(Constant.MAX_NUMBER,1)
                startActivityForResult(i, REQUEST_CODE_PICK_IMAGE)
            }else{
                // tampilkan permission request saat belum mendapat permission dari user
                EasyPermissions.requestPermissions(this,"This application need your permission to access photo gallery.",991,android.Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }

        kirim.setOnClickListener {

            send()


        }

    }

    private fun launchGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_CODE_PICK_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CODE_PICK_IMAGE && resultCode == Activity.RESULT_OK && data != null) {

                    uri=data!!.data
                buktitf.visibility=View.GONE

                val pickedImg = data.getParcelableArrayListExtra<ImageFile>(RESULT_PICK_IMAGE)[0]?.path

                // membuat request body yang berisi file dari picked image.
                val requestBody = RequestBody.create(MediaType.parse("multipart"), File(pickedImg))

                // mengoper value dari requestbody sekaligus membuat form data untuk upload. dan juga mengambil nama dari picked image
                gambar = MultipartBody.Part.createFormData("gambar",File(pickedImg)?.name,requestBody)
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                val client = OkHttpClient.Builder().addInterceptor(interceptor).connectTimeout(100, TimeUnit.SECONDS).readTimeout(100, TimeUnit.SECONDS).build()
                var retrofit = Retrofit.Builder().client(client).baseUrl(Const.base_url).addConverterFactory(GsonConverterFactory.create()).build()
                val postData = retrofit!!.create(ApiOnly::class.java)
                postData.upload(gambar!!).enqueue(object : Callback<Gmbar>{
                    override fun onFailure(call: Call<Gmbar>, t: Throwable) {
                        i("tag","error"+t.stackTrace)
                    }

                    override fun onResponse(call: Call<Gmbar>, response: Response<Gmbar>) {
                        i("tag", "succes"+response.body().toString())
                    }


                })
            gambar_.visibility = View.VISIBLE
            gambar_.text = File(pickedImg).name.toString()
                Glide.with(this).load(pickedImg).into(buktitf1)
                buktitf1.visibility = View.VISIBLE

        }
    }
    private fun send(){
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).connectTimeout(100, TimeUnit.SECONDS).readTimeout(100, TimeUnit.SECONDS).build()
        var retrofit = Retrofit.Builder().client(client).baseUrl(Const.base_url).addConverterFactory(GsonConverterFactory.create()).build()
        val postData = retrofit!!.create(ApiOnly::class.java)
        val id_ = id
        val name = nama.text.toString()
        val email = email.text.toString()
        val comment = komentar.text.toString()
        val jumlah = jumlah_donasi.text.toString()
        val namegamber = gambar_.text.toString()
        e("tag", "id $id_")
        e("tag", "sss $namegamber")

        if (name.equals("")||email.equals("")||email.equals("")||comment.equals("")||jumlah.equals("")){
            Toast.makeText(this@DetailDonasiActivity,"Kolom tidak boleh kosong...",Toast.LENGTH_SHORT).show()
        }else{
            show()
            postData.postDonasi("${id_}", "${name}", "${email}", "${comment}", "${jumlah}", "${namegamber}"
            ).enqueue(object : Callback<Donasi>{
                override fun onFailure(call: Call<Donasi>, t: Throwable) {
                    i("tag", "tes"+t)
                }

                override fun onResponse(call: Call<Donasi>, response: Response<Donasi>) {
                    i("tag", "tes"+response.body().toString())
                }

            })
        }



    }
    fun show(){
        val layoutInflater = LayoutInflater.from(this)
        val promptView = layoutInflater.inflate(R.layout.succes, null)
        val alertD = AlertDialog.Builder(this).create()
        val btn = promptView.findViewById<Button>(R.id.buttonOk)
        btn.setOnClickListener {
            val intent = Intent(this, ListDonasiActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
        alertD.setView(promptView)
        alertD.show()
    }
}
