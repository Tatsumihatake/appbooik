package com.example.appbook2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()

        Handler(Looper.getMainLooper()).postDelayed({
            // Cek di memori apakah ini pertama kali aplikasi dibuka
            val sharedPreferences = getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
            val isFirstTime = sharedPreferences.getBoolean("isFirstTimeOpen", true)

            val intent = if (isFirstTime) {
                Intent(this, OnboardingActivity::class.java)
            } else {
                Intent(this, MainActivity::class.java)
            }

            startActivity(intent)
            finish()
        }, 3000)
    }
}