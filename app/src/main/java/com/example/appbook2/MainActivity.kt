package com.example.appbook2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.appbook2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Mempertahankan ViewBinding Anda yang sudah benar
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        // HAPUS blok kode bottomNavigation.setOnItemSelectedListener lama Anda.
        // GANTIKAN dengan 3 baris kode Jetpack Navigation di bawah ini:

        // 1. Ambil NavHostFragment dari XML yang sudah kita buat
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        // 2. Ambil NavController (Sopir otomatis yang memindahkan halaman)
        val navController = navHostFragment.navController

        // 3. Sambungkan Bottom Navigation dengan NavController
        // Ini akan otomatis mencocokkan ID di menu XML dengan ID di nav_graph XML
        binding.bottomNavigation.setupWithNavController(navController)

        // (Biarkan kode tambahan lain milik Anda di bawah ini jika ada)
    }
}