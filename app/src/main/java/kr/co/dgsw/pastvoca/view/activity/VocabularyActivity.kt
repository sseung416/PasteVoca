package kr.co.dgsw.pastvoca.view.activity

import android.content.Intent
import kr.co.dgsw.pastvoca.base.BaseActivity
import kr.co.dgsw.pastvoca.databinding.ActivityVocabularyBinding
import kr.co.dgsw.pastvoca.viewmodel.activity.VocabularyViewModel
import kr.co.dgsw.pastvoca.widget.extension.startActivity
import kr.co.dgsw.pastvoca.widget.livedata.EventObserver
import kr.co.dgsw.pastvoca.widget.recyclerview.adapter.VocabularyAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class VocabularyActivity : BaseActivity<ActivityVocabularyBinding, VocabularyViewModel>() {
    override val viewModel by viewModel<VocabularyViewModel>()

    private lateinit var adapter: VocabularyAdapter

    override fun onRestart() {
        super.onRestart()
        viewModel.getVocabularyNames()
    }

    override fun init() {
        viewModel.getVocabularyNames()
        adapter = VocabularyAdapter(getSelectedPosition())

        binding.rvVoca.adapter = adapter

        binding.btnAddVoca.setOnClickListener {
            startActivity(AddVocabularyActivity::class.java)
        }

        binding.btnBackVoca.setOnClickListener {
            setResult(RESULT_OK, Intent().putExtra("vocabulary", adapter.getSelectedVocabulary()))
            finish()
        }
    }

    override fun observeViewModel() {
        viewModel.apply {
            vocabularyNames.observe(this@VocabularyActivity, EventObserver {
                adapter.setList(it)
            })
        }
    }

    private fun getSelectedPosition() = intent.getIntExtra("id", 1) - 1
}