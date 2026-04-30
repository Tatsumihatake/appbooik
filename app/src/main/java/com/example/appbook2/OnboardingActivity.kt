package com.example.appbook2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2

class OnboardingActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var layoutIndicators: LinearLayout
    private lateinit var btnSkip: TextView
    private lateinit var btnGetStarted: Button
    // Variabel btnBack/btnExit sudah dihapus secara permanen dari sini

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
        supportActionBar?.hide()

        viewPager = findViewById(R.id.viewPager)
        layoutIndicators = findViewById(R.id.layoutIndicators)
        btnSkip = findViewById(R.id.btnSkip)
        btnGetStarted = findViewById(R.id.btnGetStarted)
        // Pemanggilan findViewById untuk tombol kembali sudah dihapus

        setupViewPager()

        btnSkip.setOnClickListener { navigateToMain() }
        btnGetStarted.setOnClickListener { navigateToMain() }
        // Logika klik untuk tombol kembali sudah dihapus
    }

    private fun setupViewPager() {
        val onboardingItems = listOf(
            OnboardingItem(
                R.drawable.gambar1,
                "Only Books Can Help You",
                "Books can help you to increase your knowledge and become more successful."
            ),
            OnboardingItem(
                R.drawable.gambar2,
                "Learn Smartly",
                "It's 2022 and it's time to learn everything quickly and smartly. All books are stored in cloud."
            ),
            OnboardingItem(
                R.drawable.onboard,
                "Book Has Power To Change Everything",
                "We have a true friend in our life and the book is that. Book has power to change yourself."
            )
        )

        val adapter = OnboardingAdapter(onboardingItems)
        viewPager.adapter = adapter

        setupIndicators(onboardingItems.size)
        setCurrentIndicator(0)

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)

                // Logika menyembunyikan/menampilkan tombol kembali sudah dibuang bersih

                if (position == onboardingItems.size - 1) {
                    btnGetStarted.visibility = View.VISIBLE
                    btnSkip.visibility = View.INVISIBLE
                    layoutIndicators.visibility = View.INVISIBLE
                } else {
                    btnGetStarted.visibility = View.GONE
                    btnSkip.visibility = View.VISIBLE
                    layoutIndicators.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun setupIndicators(count: Int) {
        val indicators = arrayOfNulls<ImageView>(count)
        val layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        ).apply { setMargins(8, 0, 8, 0) }

        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext).apply {
                setImageDrawable(ContextCompat.getDrawable(applicationContext, android.R.drawable.presence_invisible))
                this.layoutParams = layoutParams
            }
            layoutIndicators.addView(indicators[i])
        }
    }

    private fun setCurrentIndicator(index: Int) {
        val childCount = layoutIndicators.childCount
        for (i in 0 until childCount) {
            val imageView = layoutIndicators.getChildAt(i) as ImageView
            if (i == index) {
                imageView.setImageDrawable(ContextCompat.getDrawable(applicationContext, android.R.drawable.presence_online))
            } else {
                imageView.setImageDrawable(ContextCompat.getDrawable(applicationContext, android.R.drawable.presence_invisible))
            }
        }
    }

    private fun navigateToMain() {
        // Menyimpan status memori agar Onboarding tidak muncul lagi besok-besok
        val sharedPreferences = getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean("isFirstTimeOpen", false).apply()

        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}