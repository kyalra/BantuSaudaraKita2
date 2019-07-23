package com.example.bantusaudarakita.view

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log.e
import com.example.bantusaudarakita.R
import com.example.bantusaudarakita.adapter.ListDonasiAdapter
import com.example.bantusaudarakita.viewModel.DataDonasiViewModel

class ListDonasiActivity : AppCompatActivity() {
    var rcView: RecyclerView? = null
    var donasiAdapter: ListDonasiAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.donasi_activity)
        rcView = findViewById(R.id.rcDonasi)
        rcView!!.setHasFixedSize(true)
        rcView!!.layoutManager = LinearLayoutManager(this)
        val viewModel = ViewModelProviders.of(this).get(DataDonasiViewModel::class.java)
        viewModel.dataDonasi.observeForever {
            e("tag", it!!.get(0).judul)
            donasiAdapter = ListDonasiAdapter(this@ListDonasiActivity, it)
            rcView!!.adapter = donasiAdapter
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}