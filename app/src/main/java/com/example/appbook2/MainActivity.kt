package com.example.appbook2

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appbook2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Menggunakan ViewBinding sesuai standar industri
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        // TODO: FASE 1 - Nantinya ini akan dihubungkan dengan Navigation Component.
        // Untuk sementara, kita berikan logika dasar pada BottomNavigationView
        // Kita telah menghapus sistem View.GONE / View.VISIBLE yang merusak memori.

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId) {
                // R.id.nav_home -> loadFragment(HomeFragment()) // Akan diaktifkan setelah fragment dibuat
                // R.id.nav_search -> loadFragment(SearchFragment())
                // R.id.nav_library -> loadFragment(LibraryFragment())
            }
            true
        }

        Toast.makeText(this, "Sistem Navigasi sedang direfaktor menuju standar industri.", Toast.LENGTH_LONG).show()
    }
}