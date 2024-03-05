package com.nalldev.mystarterproject.presentation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.nalldev.mystarterproject.R
import com.nalldev.mystarterproject.databinding.ActivityDetailBinding
import eightbitlab.com.blurview.RenderScriptBlur

class DetailActivity : AppCompatActivity() {
    private var _binding : ActivityDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            ivBlurBg.setBackgroundResource(R.drawable.nal)
            ivMain.setBackgroundResource(R.drawable.nal)
        }

        binding.blurLayout.setupWith(binding.root, RenderScriptBlur(this@DetailActivity))
            .setBlurRadius(20f)
    }
}