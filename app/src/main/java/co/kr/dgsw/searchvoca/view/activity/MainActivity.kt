package co.kr.dgsw.searchvoca.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import co.kr.dgsw.searchvoca.R
import co.kr.dgsw.searchvoca.view.fragment.HomeFragment
import co.kr.dgsw.searchvoca.view.fragment.SettingFragment
import co.kr.dgsw.searchvoca.view.fragment.WordTestFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        changeFragment(HomeFragment())

        findViewById<BottomNavigationView>(R.id.bottom_main).apply {
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.menu_home -> changeFragment(HomeFragment())
                    R.id.menu_test -> changeFragment(WordTestFragment())
                    R.id.menu_setting -> changeFragment(SettingFragment())
                }
                true
            }
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.linear_main, fragment).commit()
    }
}