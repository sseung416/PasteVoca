package co.kr.dgsw.searchvoca.view.fragment

import android.view.View.VISIBLE
import co.kr.dgsw.searchvoca.base.BaseFragment
import co.kr.dgsw.searchvoca.databinding.FragmentHomeBinding
import co.kr.dgsw.searchvoca.datasource.model.dto.VocabularyName
import co.kr.dgsw.searchvoca.view.activity.AddWordActivity
import co.kr.dgsw.searchvoca.view.activity.SearchWordActivity
import co.kr.dgsw.searchvoca.view.dialog.TextBottomSheetDialog
import co.kr.dgsw.searchvoca.view.dialog.WordBottomSheetDialog
import co.kr.dgsw.searchvoca.viewmodel.dialog.TextBottomSheetViewModel
import co.kr.dgsw.searchvoca.viewmodel.fragment.HomeViewModel
import co.kr.dgsw.searchvoca.viewmodel.dialog.WordBottomSheetViewModel
import co.kr.dgsw.searchvoca.widget.extension.startActivity
import co.kr.dgsw.searchvoca.widget.livedata.EventObserver
import co.kr.dgsw.searchvoca.widget.view.adapter.WordAdapter
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val viewModel by sharedViewModel<HomeViewModel>()
    private val wordDialogViewModel by sharedViewModel<WordBottomSheetViewModel>()
    private val textBottomSheetViewModel by sharedViewModel<TextBottomSheetViewModel>()

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
            allWords.observe(viewLifecycleOwner, EventObserver {
                wordAdapter.setList(it)
            })

            wordsByVoca.observe(viewLifecycleOwner, EventObserver {
                wordAdapter.setList(it)
            })

            searchWords.observe(viewLifecycleOwner, EventObserver {
                if (it.isNotEmpty()) {
                    binding.cvWord.visibility = VISIBLE
                    binding.tvSearchTitle.text = "검색한 단어가 ${it.size}개가 있어요!"
                }
            })

            textBottomSheetViewModel.clickedItem.observe(viewLifecycleOwner, EventObserver {
                when (it) {
                    null ->
                        getAllWords()
                    WordAdapter.SHUFFLE, WordAdapter.SORT_EASY, WordAdapter.SORT_DIFFICULT ->
                        // todo 섞인 아이템 룸에 저장
                        wordAdapter.sort(it)
                    else ->
                        getWordsByVocabulary(it)
                }
            })
        }

        wordDialogViewModel.deleteEvent.observe(viewLifecycleOwner) {
            viewModel.getAllWords()
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.getAllWords()
        viewModel.getSearchWords()
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

        binding.btnSortHome.setOnClickListener {
            // todo data class 이름 바꾸기
            val list = listOf(
                VocabularyName(WordAdapter.SHUFFLE, "랜덤"),
                VocabularyName(WordAdapter.SORT_DIFFICULT, "어려운순으로"),
                VocabularyName(WordAdapter.SORT_EASY, "쉬운순으로"),
            )
            TextBottomSheetDialog(list).show(parentFragmentManager, "")
        }

        binding.layoutSpinner.setOnClickListener {
            val list = arrayListOf<VocabularyName>().apply {
                val vocabularyNames = viewModel.vocabularyNames.value?.peekContent() ?: listOf()
                add(VocabularyName(null, "전체"))
                addAll(vocabularyNames)
            }
            TextBottomSheetDialog(list).show(parentFragmentManager, "")
        }

        binding.cvWord.setOnClickListener {
            requireActivity().startActivity(SearchWordActivity::class.java)
        }
    }
}