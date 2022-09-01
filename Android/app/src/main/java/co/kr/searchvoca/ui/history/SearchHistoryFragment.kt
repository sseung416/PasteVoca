package co.kr.searchvoca.ui.history

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import co.kr.searchvoca.R
import co.kr.searchvoca.databinding.FragmentSearchHistoryBinding
import co.kr.searchvoca.ui.BindingFragment
import co.kr.searchvoca.ui.bind
import co.kr.searchvoca.ui.history.SearchHistoryAction.*
import co.kr.searchvoca.ui.launchAndRepeatWithViewLifecycle
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchHistoryFragment :
    BindingFragment<FragmentSearchHistoryBinding>(R.layout.fragment_search_history) {

    private val viewModel by viewModel<SearchHistoryViewModel>()

    private val adapter = SearchHistoryListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bind {
            vm = viewModel
            adapter = this@SearchHistoryFragment.adapter
        }

        observeState()
        setupToolbar()
    }

    private fun observeState() {
        launchAndRepeatWithViewLifecycle {
            viewModel.action.collect {
                when (it) {
                    RemoveCheckedItems -> adapter.removeCheckedItems()
                }
            }
        }
    }

    private fun setupToolbar() {
        binding.toolbar.setOnMenuItemClickListener {
            return@setOnMenuItemClickListener when (it.itemId) {
                R.id.action_select_all -> {
                    for (i in 0 until adapter.itemCount) {
                        val holder =
                            binding.rvWord.findViewHolderForAdapterPosition(i) as SearchHistoryViewHolder
                        holder.select()
                    }
                    true
                }

                R.id.action_add_word -> {
                    viewModel.addWord(adapter.getCheckedItemIds())
                    true
                }

                R.id.action_delete_history -> {
                    viewModel.deleteHistory(adapter.getCheckedItemIds())
                    true
                }

                else -> false
            }
        }

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
}