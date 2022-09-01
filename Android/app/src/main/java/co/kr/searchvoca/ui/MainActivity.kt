package co.kr.searchvoca.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import co.kr.searchvoca.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_main)

        bottomNavigationView.setupWithNavController(navHostFragment.navController)
        bottomNavigationView.itemIconTintList = null
    }
}