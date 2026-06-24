package com.example.appbook2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.appbook2.databinding.ActivityMainBinding // Nanti diganti binding spesifik fragment

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Untuk sementara kita gunakan text sederhana sebelum kita desain UI aslinya
        return inflater.inflate(android.R.layout.simple_list_item_1, container, false)
    }
}
