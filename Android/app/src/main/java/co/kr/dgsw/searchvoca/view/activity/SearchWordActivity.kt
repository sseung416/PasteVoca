package co.kr.dgsw.searchvoca.view.activity

import android.widget.Toast
import co.kr.dgsw.searchvoca.base.BaseActivity
import co.kr.dgsw.searchvoca.databinding.ActivitySearchWordBinding
import co.kr.dgsw.searchvoca.datasource.model.dto.Vocabulary
import co.kr.dgsw.searchvoca.viewmodel.activity.SearchWordViewModel
import co.kr.dgsw.searchvoca.widget.livedata.EventObserver
import co.kr.dgsw.searchvoca.widget.view.adapter.WordSearchAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchWordActivity : BaseActivity<ActivitySearchWordBinding, SearchWordViewModel>() {
    override val viewModel by viewModel<SearchWordViewModel>()
    private val adapter = WordSearchAdapter()

    override fun init() {
        viewModel.getSearchWords()
        binding.rvWord.adapter = adapter
        setupButton()
    }

    override fun observeViewModel() {
        viewModel.words.observe(this, EventObserver {
            adapter.setList(it)
        })

        viewModel.delete.observe(this) {
            adapter.removeCheckedItems()
        }

        viewModel.update.observe(this) {
            adapter.removeCheckedItems()
        }
    }

    private fun setupButton() {
        binding.btnSelectAll.setOnClickListener {
//            adapter.itemCount.
//            binding.rvWord.findViewHolderForAdapterPosition()
        }

        binding.btnInsert.setOnClickListener {
            // todo 단어장 선택
            viewModel.updateVocabulary(adapter.getCheckedWordId(), Vocabulary.VOCABULARY_ID_NO_NAMED)
        }

        binding.btnDelete.setOnClickListener {
            viewModel.delete(adapter.getCheckedWordId())
        }
    }
}