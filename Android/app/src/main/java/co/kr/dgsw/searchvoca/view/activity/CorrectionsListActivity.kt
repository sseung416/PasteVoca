package co.kr.dgsw.searchvoca.view.activity

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.view.View.VISIBLE
import co.kr.dgsw.searchvoca.R
import co.kr.dgsw.searchvoca.base.BaseActivity
import co.kr.dgsw.searchvoca.databinding.ActivityCorrectionsListBinding
import co.kr.dgsw.searchvoca.viewmodel.activity.CorrectionsListViewModel
import co.kr.dgsw.searchvoca.widget.livedata.EventObserver
import co.kr.dgsw.searchvoca.widget.view.adapter.CorrectionsListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class CorrectionsListActivity : BaseActivity<ActivityCorrectionsListBinding, CorrectionsListViewModel>() {
    override val viewModel by viewModel<CorrectionsListViewModel>()
    private val adapter = CorrectionsListAdapter()

    override fun init() {
        viewModel.getAllCorrections()
        binding.rvCorrectionsList.adapter = adapter.apply {
            onClickItemListener = {
                moveToCorrections(it.id!!)
            }

            onClickDeleteListener = {
                viewModel.deleteCorrections(it)
            }
        }

        setupToolbar()
    }

    override fun observeViewModel() {
        viewModel.correctionsList.observe(this, EventObserver {
            if (it.isEmpty()) binding.tvEmpty.visibility = VISIBLE
            else adapter.setList(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_default, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbarCorrectionsList)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    // 오답노트로 이동
    private fun moveToCorrections(id: Int) {
        val intent = Intent(this, CorrectionsActivity::class.java)
            .putExtra("vocabularyId", id)
        startActivity(intent)
    }
}