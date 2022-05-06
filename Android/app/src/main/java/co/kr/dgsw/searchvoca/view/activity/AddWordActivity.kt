package co.kr.dgsw.searchvoca.view.activity

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import co.kr.dgsw.searchvoca.R
import co.kr.dgsw.searchvoca.databinding.ActivityAddWordBinding

class AddWordActivity : AppCompatActivity() {
    private val binding by lazy { ActivityAddWordBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupToolbar()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_add, menu)
        return true
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbarMain)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            setDisplayHomeAsUpEnabled(true)
        }
    }
}