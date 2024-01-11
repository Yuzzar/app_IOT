package com.example.app_projectiot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class Splash : AppCompatActivity() {

    private val SPLASH_TIME:Long=3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        installSplashScreen()
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            startActivity(Intent(this, Login::class.java))
            finish()
        },SPLASH_TIME)
    }
    }
