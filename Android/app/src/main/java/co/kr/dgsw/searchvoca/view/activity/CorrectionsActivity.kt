package co.kr.dgsw.searchvoca.view.activity

import android.view.Menu
import android.view.MenuItem
import co.kr.dgsw.searchvoca.R
import co.kr.dgsw.searchvoca.base.BaseActivity
import co.kr.dgsw.searchvoca.databinding.ActivityCorrectionsBinding
import co.kr.dgsw.searchvoca.view.dialog.WordTestDialog
import co.kr.dgsw.searchvoca.viewmodel.activity.CorrectionsViewModel
import co.kr.dgsw.searchvoca.widget.extension.startActivityWithFinish
import co.kr.dgsw.searchvoca.widget.livedata.EventObserver
import co.kr.dgsw.searchvoca.widget.view.adapter.CorrectionsAdapter
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.android.ext.android.inject

class CorrectionsActivity : BaseActivity<ActivityCorrectionsBinding, CorrectionsViewModel>() {
    override val viewModel by inject<CorrectionsViewModel>()
    private val adapter = CorrectionsAdapter()

    override fun init() {
        setupData()
        setupView()
        setupToolbar()
    }

    override fun observeViewModel() {
        viewModel.apply {
            allList.observe(this@CorrectionsActivity, EventObserver {
                setList(it)
                adapter.setList(getList())
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_corrections, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                startActivityWithFinish(MainActivity::class.java)
                true
            }
            R.id.toolbar_resolve_corrections -> {
                WordTestDialog().show(supportFragmentManager, "")
                true
            }
            else -> false
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbarCorrections)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(false)
        }
    }

    private fun setupView() {
        binding.viewpager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewpager) { tab, pos ->
            tab.text = when (pos) {
                0 -> getString(R.string.wrong)
                1 -> getString(R.string.correct)
                2 -> getString(R.string.all)
                else -> "아잉"
            }
        }.attach()
    }

    private fun setupData() {
        val id = intent.getIntExtra("vocabularyId", 0)
        viewModel.getCorrectionsWordList(id)
    }
}