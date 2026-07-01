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

        // Cek apakah ini pertama kali aplikasi dibuka
        val sharedPreferences = getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
        val isFirstTime = sharedPreferences.getBoolean("isFirstTimeOpen", true)

        Handler(Looper.getMainLooper()).postDelayed({
            if (isFirstTime) {
                // Jika baru pertama kali, masuk ke Onboarding
                startActivity(Intent(this, OnboardingActivity::class.java))
            } else {
                // Jika sudah pernah buka, langsung ke Main
                startActivity(Intent(this, MainActivity::class.java))
            }
            finish()
        }, 2000)
    }
}
