package kr.co.dgsw.pastvoca.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kr.co.dgsw.pastvoca.R
import kr.co.dgsw.pastvoca.view.fragment.HomeFragment
import kr.co.dgsw.pastvoca.view.fragment.SettingFragment
import kr.co.dgsw.pastvoca.view.fragment.WordTestFragment

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