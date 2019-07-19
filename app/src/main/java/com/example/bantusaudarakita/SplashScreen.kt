package com.example.bantusaudarakita

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        Handler().postDelayed({
            val menu = Intent(this, DataMenuUtama::class.java)
            startActivity(menu)
            finish()

        },3000)
    }
}