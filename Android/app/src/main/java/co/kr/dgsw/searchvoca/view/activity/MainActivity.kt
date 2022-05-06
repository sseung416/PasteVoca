package co.kr.dgsw.searchvoca.view.activity

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import co.kr.dgsw.searchvoca.R
import co.kr.dgsw.searchvoca.view.fragment.HomeFragment
import co.kr.dgsw.searchvoca.view.fragment.SettingFragment
import co.kr.dgsw.searchvoca.view.fragment.WordTestFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupToolbar()
        changeFragment(HomeFragment())

        findViewById<BottomNavigationView>(R.id.bottom_main).setOnItemSelectedListener {
            return@setOnItemSelectedListener when (it.itemId) {
                R.id.menu_home -> changeFragment(HomeFragment())
                R.id.menu_test -> changeFragment(WordTestFragment())
                R.id.menu_setting -> changeFragment(SettingFragment())
                else -> false
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_default, menu)
        return true
    }

    private fun setupToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar2_main))
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun changeFragment(fragment: Fragment): Boolean {
        supportFragmentManager.beginTransaction().replace(R.id.fragment, fragment).commit()
        return true
    }
}