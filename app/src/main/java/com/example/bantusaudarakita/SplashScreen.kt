package com.example.bantusaudarakita

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.example.bantusaudarakita.view.DetailDonasiActivity
import com.example.bantusaudarakita.view.ListDonasiActivity

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        Handler().postDelayed({
            val menu = Intent(this, ListDonasiActivity::class.java)
            startActivity(menu)
            finish()

        },3000)
    }
}