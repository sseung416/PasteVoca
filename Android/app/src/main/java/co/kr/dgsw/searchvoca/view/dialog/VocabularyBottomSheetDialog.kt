package co.kr.dgsw.searchvoca.view.dialog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import co.kr.dgsw.searchvoca.R
import co.kr.dgsw.searchvoca.base.BaseBottomSheetDialog
import co.kr.dgsw.searchvoca.databinding.DialogBottomSheetVocabularyBinding
import co.kr.dgsw.searchvoca.viewmodel.dialog.VocabularyBottomSheetViewModel
import co.kr.dgsw.searchvoca.widget.livedata.Event
import co.kr.dgsw.searchvoca.widget.livedata.EventObserver
import co.kr.dgsw.searchvoca.widget.view.adapter.VocabularyBottomSheetAdapter
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class VocabularyBottomSheetDialog: BaseBottomSheetDialog<DialogBottomSheetVocabularyBinding>() {
    override val viewModel by sharedViewModel<VocabularyBottomSheetViewModel>()
    private val adapter = VocabularyBottomSheetAdapter()

    private val navController by lazy { findNavController() }

    override fun init() {
        viewModel.getVocabulary()

        binding.rvVoca.adapter = adapter.apply {
            onClickItemListener = {
                viewModel.clickedItem.value = Event(it)
                dismiss()
            }

            onClickAddVocabulary = {
                navController.navigate(R.id.action_addWordFragment_to_addVocabularyFragment)
                dismiss()
            }
        }
    }

    override fun observeViewModel() {
        viewModel.vocabularyNameList.observe(viewLifecycleOwner, EventObserver {
            adapter.setList(it)
        })
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): DialogBottomSheetVocabularyBinding = DialogBottomSheetVocabularyBinding.inflate(inflater)
}