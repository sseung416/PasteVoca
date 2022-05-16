package co.kr.dgsw.searchvoca.view.fragment

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import co.kr.dgsw.searchvoca.R
import co.kr.dgsw.searchvoca.base.BaseFragment
import co.kr.dgsw.searchvoca.databinding.FragmentHomeBinding
import co.kr.dgsw.searchvoca.view.activity.AddWordActivity
import co.kr.dgsw.searchvoca.view.activity.SearchWordActivity
import co.kr.dgsw.searchvoca.view.dialog.DefaultBottomSheetDialog
import co.kr.dgsw.searchvoca.view.dialog.WordBottomSheetDialog
import co.kr.dgsw.searchvoca.viewmodel.dialog.WordBottomSheetViewModel
import co.kr.dgsw.searchvoca.viewmodel.fragment.HomeViewModel
import co.kr.dgsw.searchvoca.widget.extension.setOnTouchListenerThrottled
import co.kr.dgsw.searchvoca.widget.extension.startActivity
import co.kr.dgsw.searchvoca.widget.livedata.EventObserver
import co.kr.dgsw.searchvoca.widget.view.adapter.WordAdapter
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val viewModel by viewModel<HomeViewModel>()
    private val wordDialogViewModel by sharedViewModel<WordBottomSheetViewModel>()

    private val wordAdapter = WordAdapter()

    override fun init() {
        viewModel.getVocabularyNames()
        viewModel.getAllWords()
        viewModel.getSearchWords()

        setupToolbar()
        setupRecyclerView()
        setupButton()
    }

    override fun observeViewModel() {
        viewModel.apply {
            allWords.observe(viewLifecycleOwner, EventObserver {
                wordAdapter.setList(it)
            })

            wordsByVoca.observe(viewLifecycleOwner, EventObserver {
                if (it.isEmpty())
                    Toast.makeText(requireContext(), "단어가 없어요! 단어를 추가해주세요.", Toast.LENGTH_LONG).show()
                else wordAdapter.setList(it)
            })
        }

        wordDialogViewModel.deleteEvent.observe(viewLifecycleOwner) {
            viewModel.getAllWords()
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.apply {
            getAllWords()
            getSearchWords()
            getVocabularyNames()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.toolbar_home, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.toolbar_filter_home -> {
                val list = listOf(
                    Pair(WordAdapter.SHUFFLE, "랜덤"),
                    Pair(WordAdapter.SORT_DIFFICULT, "어려운순으로"),
                    Pair(WordAdapter.SORT_EASY, "쉬운순으로"),
                )

                DefaultBottomSheetDialog(list, { data ->
                    // todo 단어 순서 저장
                    wordAdapter.sort(data.first!!)
                }).show(parentFragmentManager, "")
                true
            }

            R.id.toolbar_add_home -> {
                requireActivity().startActivity(AddWordActivity::class.java)
                true
            }
            else -> false
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
        binding.cvWord.setOnClickListener {
            requireActivity().startActivity(SearchWordActivity::class.java)
        }
    }

    private fun setupToolbar() {
        requireActivity().findViewById<TextView>(R.id.toolbar_title_main).visibility = GONE

        requireActivity().findViewById<Spinner>(R.id.toolbar_spinner_main).apply {
            visibility = VISIBLE

            setOnTouchListenerThrottled({
                val list = arrayListOf(Pair<Int?, String>(null, "전체")).apply {
                    val vocabularyList = viewModel.vocabularyNames.value?.peekContent()
                        ?.map { Pair(it.id, it.name!!) } ?: listOf()
                    addAll(vocabularyList)
                }

                DefaultBottomSheetDialog(list, {
                    if (it.first == null) {
                        viewModel.getAllWords()
                    } else {
                        viewModel.getWordsByVocabulary(it.first!!)
                    }
                }).show(parentFragmentManager, "")
            })
        }

    }
}