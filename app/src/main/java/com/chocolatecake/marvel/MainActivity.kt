package com.chocolatecake.marvel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.chocolatecake.marvel.databinding.ActivityMainBinding
import com.chocolatecake.marvel.databinding.FragmentComicsBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }


    override fun onResume() {
        super.onResume()
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.searchFragment,
                R.id.seriesFragment,
                R.id.comicsFragment3,
            )
        )
        val navController = findNavController(R.id.fragment_host)
        binding.mainBottomNavigation.setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBarConfiguration)
//
//        setBottomNavigationVisibility(navController)
//        setNavigationController(navController)

//        val navControl = findNavController(R.id.fragment_host)
//        NavigationUI.setupActionBarWithNavController(this,navControl)
//
//        binding.mainBottomNavigation.setupWithNavController(navControl)
    }

//    private fun setBottomNavigationVisibility(navController: NavController) {
//        navController.addOnDestinationChangedListener { _, destination, _ ->
//            binding.mainBottomNavigation.isVisible = destination.id != R.id.loginFragment
//        }
//    }
//
//    private fun setNavigationController(navController: NavController) {
//        binding.bottomNavigation.setOnItemSelectedListener { item ->
//            NavigationUI.onNavDestinationSelected(item, navController)
//            navController.popBackStack(item.itemId, inclusive = false)
//            true
//        }
//    }

}