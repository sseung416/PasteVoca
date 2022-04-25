package co.kr.dgsw.searchvoca.view.dialog

import android.view.LayoutInflater
import android.view.ViewGroup
import co.kr.dgsw.searchvoca.base.BaseBottomSheetDialog
import co.kr.dgsw.searchvoca.databinding.DialogBottomSheetTextBinding
import co.kr.dgsw.searchvoca.datasource.model.dto.VocabularyName
import co.kr.dgsw.searchvoca.viewmodel.dialog.TextBottomSheetViewModel
import co.kr.dgsw.searchvoca.widget.livedata.Event
import co.kr.dgsw.searchvoca.widget.view.adapter.BottomSheetAdapter
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class TextBottomSheetDialog(
    vocabularyList: List<VocabularyName>
) : BaseBottomSheetDialog<DialogBottomSheetTextBinding>() {
    override val viewModel by sharedViewModel<TextBottomSheetViewModel>()
    private val adapter = BottomSheetAdapter(vocabularyList)

    override fun init() {
        binding.rvVoca.adapter = adapter.apply {
            onClickItemListener = {
                viewModel.clickedItem.value = Event(it)
                dismiss()
            }
        }
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): DialogBottomSheetTextBinding = DialogBottomSheetTextBinding.inflate(inflater)
}