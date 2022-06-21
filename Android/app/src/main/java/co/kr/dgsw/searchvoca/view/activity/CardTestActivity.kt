package co.kr.dgsw.searchvoca.view.activity

import android.view.Menu
import co.kr.dgsw.searchvoca.R
import co.kr.dgsw.searchvoca.base.BaseActivity
import co.kr.dgsw.searchvoca.databinding.ActivityCardTestBinding
import co.kr.dgsw.searchvoca.datasource.model.dto.VocabularyName
import co.kr.dgsw.searchvoca.viewmodel.fragment.CardTestViewModel
import co.kr.dgsw.searchvoca.widget.view.CardStackAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class CardTestActivity : BaseActivity<ActivityCardTestBinding, CardTestViewModel>(), CardStackAdapter {
    override val viewModel by viewModel<CardTestViewModel>()

    override fun init() {
        val vocabulary = intent?.getSerializableExtra("vocabulary") as? VocabularyName
        binding.toolbarTitleCardTest.text = vocabulary?.name

        setupToolbar()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_default, menu)
        return true
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbarCardTest)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }
    }
}