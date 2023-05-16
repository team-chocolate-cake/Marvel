package com.chocolatecake.marvel

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.chocolatecake.marvel.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        val navController = findNavController(R.id.fragment_host)
        binding.mainBottomNavigation.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.searchFragment,
                R.id.homeFragment,
                R.id.storiesFragment,
                R.id.latestSeriesFragment -> showBottomNav()
                else -> hideBottomNav()
            }
        }
    }

    private fun showBottomNav() {
        binding.mainBottomNavigation.apply {
            visibility = View.VISIBLE
            animate()
                .translationY(0f)
                .setDuration(300)
                .start()
        }
    }

    private fun hideBottomNav() {
        binding.mainBottomNavigation.apply {
            animate()
                .translationY(height.toFloat())
                .setDuration(300)
                .withEndAction {
                    visibility = View.GONE
                }
                .start()
        }
    }

}