package com.nalldev.mystarterproject.presentation

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.chip.Chip
import com.nalldev.mystarterproject.R
import com.nalldev.mystarterproject.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<StarterViewModel>()

    private val adapter by lazy {
        StarterAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setContentView(binding.root)

        setupObserver()
        setupUI()
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.personsFlow.collectLatest { pagingData ->

                }
            }
        }
    }

    private fun setupUI() = with(binding) {
        createChip()
    }

    private fun createChip() {
        binding.chipGroup.setOnCheckedStateChangeListener { chipGroup, ints ->
            val chipText = when (chipGroup.checkedChipId) {
                R.id.chip_breaking -> "Breaking"
                R.id.chip_sport -> "Sport"
                R.id.chip_tech -> "Tech"
                else -> ""
            }

            Log.e("ERROR NIH", chipText)
        }
    }
}