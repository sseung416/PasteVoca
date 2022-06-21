package co.kr.dgsw.searchvoca.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import co.kr.dgsw.searchvoca.R
import co.kr.dgsw.searchvoca.databinding.ActivityListeningTestBinding

class ListeningTestActivity : AppCompatActivity() {
    private val binding by lazy { ActivityListeningTestBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupToolbar()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_default, menu)
        return true
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbarListeningTest)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            setDisplayHomeAsUpEnabled(true)
        }
    }
}