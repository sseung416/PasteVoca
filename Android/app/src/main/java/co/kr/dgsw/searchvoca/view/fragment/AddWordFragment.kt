package co.kr.dgsw.searchvoca.view.fragment

import android.view.MenuItem
import android.widget.Toast
import co.kr.dgsw.searchvoca.R
import co.kr.dgsw.searchvoca.base.BaseFragment
import co.kr.dgsw.searchvoca.databinding.FragmentAddWordBinding
import co.kr.dgsw.searchvoca.view.dialog.SearchResultDialog
import co.kr.dgsw.searchvoca.view.dialog.VocabularyBottomSheetDialog
import co.kr.dgsw.searchvoca.viewmodel.dialog.VocabularyBottomSheetViewModel
import co.kr.dgsw.searchvoca.viewmodel.fragment.UpdateWordViewModel
import co.kr.dgsw.searchvoca.widget.livedata.EventObserver
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddWordFragment : BaseFragment<FragmentAddWordBinding, UpdateWordViewModel>() {
    override val viewModel by viewModel<UpdateWordViewModel>()
    private val vocabularyBottomSheetViewModel by sharedViewModel<VocabularyBottomSheetViewModel>()

    override fun init() {
        setHasOptionsMenu(true)
    }

    override fun observeViewModel() {
        viewModel.apply {
            vocabularyTouchEvent.observe(viewLifecycleOwner) {
                VocabularyBottomSheetDialog().show(parentFragmentManager, "")
            }

            definitionList.observe(viewLifecycleOwner, EventObserver {
                SearchResultDialog(it).show(parentFragmentManager, "")
            })

            insertEvent.observe(viewLifecycleOwner) {
                requireActivity().finish()
            }

            errorMessage.observe(viewLifecycleOwner, EventObserver {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            })

            vocabularyBottomSheetViewModel.clickedItem.observe(viewLifecycleOwner, EventObserver {
                vocabulary.value = it
            })
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> requireActivity().finish()
            R.id.toolbar_confirm_add -> viewModel.insertWord()
        }
        return true
    }
}