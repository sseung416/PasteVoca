package co.kr.dgsw.searchvoca.view.fragment

import android.view.View.VISIBLE
import co.kr.dgsw.searchvoca.base.BaseFragment
import co.kr.dgsw.searchvoca.databinding.FragmentHomeBinding
import co.kr.dgsw.searchvoca.datasource.model.dto.VocabularyName
import co.kr.dgsw.searchvoca.view.activity.AddWordActivity
import co.kr.dgsw.searchvoca.view.dialog.VocabularyBottomSheetDialog
import co.kr.dgsw.searchvoca.view.dialog.WordBottomSheetDialog
import co.kr.dgsw.searchvoca.viewmodel.fragment.HomeViewModel
import co.kr.dgsw.searchvoca.viewmodel.fragment.WordBottomSheetViewModel
import co.kr.dgsw.searchvoca.widget.extension.startActivity
import co.kr.dgsw.searchvoca.widget.livedata.EventObserver
import co.kr.dgsw.searchvoca.widget.view.adapter.WordAdapter
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val viewModel by sharedViewModel<HomeViewModel>()
    private val wordBottomSheetViewModel by sharedViewModel<WordBottomSheetViewModel>()

    private val wordAdapter = WordAdapter()

    override fun init() {
        viewModel.getVocabularyNames()
        viewModel.getAllWords()
        viewModel.getSearchWords()

        setupRecyclerView()
        setupButton()
    }

    override fun observeViewModel() {
        viewModel.apply {
            vocabularyId.observe(this@HomeFragment, EventObserver {
                if (it == null) getAllWords()
                else getWordsByVocabulary(it)
            })

            allWords.observe(this@HomeFragment, EventObserver {
                wordAdapter.setList(it)
            })

            wordsByVoca.observe(this@HomeFragment, EventObserver {
                wordAdapter.setList(it)
            })

            searchWords.observe(this@HomeFragment, EventObserver {
                if (it.isNotEmpty()) {
                    binding.cvWord.visibility = VISIBLE
                    binding.tvSearchTitle.text = "검색한 단어가 ${it.size}개가 있어요!"
                }
            })
        }

        wordBottomSheetViewModel.deleteEvent.observe(this) {
            viewModel.getAllWords()
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.getAllWords()
    }

    private fun setupRecyclerView() {
        binding.rvHome.adapter = wordAdapter.apply {
            onLongClickWordListener = listener@{
                WordBottomSheetDialog(it).show(parentFragmentManager, WordBottomSheetDialog.TAG)
                return@listener true
            }

            onClickTypeListener = {
                viewModel.updateWord(it)
            }
        }
    }

    private fun setupButton() {
        binding.btnAddWordHome.setOnClickListener {
            requireActivity().startActivity(AddWordActivity::class.java)
        }

        binding.layoutSpinner.setOnClickListener {
            val list = arrayListOf<VocabularyName>()
            val vocabularyNames = viewModel.vocabularyNames.value?.peekContent() ?: listOf()

            list.add(VocabularyName(null, "전체"))
            list.addAll(vocabularyNames)
            VocabularyBottomSheetDialog(list).show(parentFragmentManager, "")
        }
    }
}