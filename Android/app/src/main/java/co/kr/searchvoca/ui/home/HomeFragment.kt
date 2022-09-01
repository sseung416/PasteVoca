package co.kr.searchvoca.ui.home

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import co.kr.searchvoca.R
import co.kr.searchvoca.databinding.FragmentHomeBinding
import co.kr.searchvoca.ui.BindingFragment
import co.kr.searchvoca.ui.bind
import co.kr.searchvoca.ui.dialog.vocabulary.VocabularyBottomSheetDialog
import co.kr.searchvoca.ui.word.UpdateTabType
import co.kr.searchvoca.domain.model.Word
import co.kr.searchvoca.shared.android.extension.setOnClickListenerThrottled
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val viewModel by viewModel<HomeViewModel>()

    private val adapter by lazy {
        WordListAdapter(::showWordUpdateDialog, ::navigateSearchHistory, viewModel::updateWordLevel)
    }

    private val navController by lazy { findNavController() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bind {
            vm = viewModel
            adapter = this@HomeFragment.adapter
        }

        setupToolbar()
    }

    private fun setupToolbar() {
        binding.toolbarHome.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.toolbar_filter_home -> {
                    WordFilterDialog { pair ->
                        adapter.sort(pair.toSortType())
                    }.show(parentFragmentManager, "")
                    true
                }

                R.id.toolbar_add_home -> {
                    navigateUpdateWord(UpdateTabType.CREATE)
                    true
                }

                else -> false
            }
        }

        binding.toolbarSpinnerHome.setOnClickListenerThrottled {
            VocabularyBottomSheetDialog({ vocabulary ->
                val spinnerTextView = binding.toolbarTvSpinnerHome

                // 같은 단어장을 선택했을 시 실행 X
                if (vocabulary.name != spinnerTextView.text) {
                    viewModel.loadWords(vocabulary.id)
                    spinnerTextView.text = vocabulary.name
                }
            }).show(parentFragmentManager, "")
        }
    }

    private fun showWordUpdateDialog(word: Word): Boolean {
        WordSettingDialog(
            {
                navigateUpdateWord(UpdateTabType.EDIT, word)
            }, {
                viewModel.deleteWord(word.id!!)
                adapter.remove(WordListItem.WordItem(word))
            }
        ).show(parentFragmentManager, "")
        return true
    }

    private fun navigateSearchHistory() {
        navController.navigate(HomeFragmentDirections.actionToSearchHistory())
    }

    private fun navigateUpdateWord(type: UpdateTabType, word: Word? = null) {
        navController.navigate(HomeFragmentDirections.actionToUpdateWord(type, word))
    }
}