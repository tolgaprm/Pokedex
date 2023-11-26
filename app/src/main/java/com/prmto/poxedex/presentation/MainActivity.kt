package com.prmto.poxedex.presentation

import android.os.Build
import android.os.Bundle
import android.window.OnBackInvokedDispatcher
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.prmto.poxedex.R
import com.prmto.poxedex.databinding.ActivityMainBinding
import com.prmto.poxedex.presentation.util.setStatusBarColorToPrimary
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handleBackPressed()
    }

    private fun handleBackPressed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            onBackInvokedDispatcher.registerOnBackInvokedCallback(OnBackInvokedDispatcher.PRIORITY_OVERLAY) {
                setStatusBarColorToPrimary()
                onBackPressedDispatcher.onBackPressed()
            }
        } else {
            onBackPressedDispatcher.addCallback(this) {
                handleNavigation()
            }
        }
    }

    private fun handleNavigation() {
        val navHost =
            supportFragmentManager.findFragmentById(binding.fragmentContainerView.id) as NavHostFragment
        val navController = navHost.navController

        navController.currentDestination?.let { destination ->
            when (destination.id) {
                R.id.poxedexListFragment -> finish()
                R.id.poxedexDetailFragment -> {
                    setStatusBarColorToPrimary()
                    navController.popBackStack()
                }

                else -> return
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}