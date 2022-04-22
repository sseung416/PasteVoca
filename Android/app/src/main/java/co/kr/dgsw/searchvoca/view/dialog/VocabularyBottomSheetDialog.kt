package co.kr.dgsw.searchvoca.view.dialog

import android.view.LayoutInflater
import android.view.ViewGroup
import co.kr.dgsw.searchvoca.base.BaseBottomSheetDialog
import co.kr.dgsw.searchvoca.databinding.DialogBottomSheetVocabularyBinding
import co.kr.dgsw.searchvoca.datasource.model.dto.VocabularyName
import co.kr.dgsw.searchvoca.viewmodel.dialog.TextBottomSheetDialog
import co.kr.dgsw.searchvoca.widget.livedata.Event
import co.kr.dgsw.searchvoca.widget.view.adapter.VocabularySpinnerAdapter
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class VocabularyBottomSheetDialog(
    vocabularyList: List<VocabularyName>
) : BaseBottomSheetDialog<DialogBottomSheetVocabularyBinding>() {
    override val viewModel by sharedViewModel<TextBottomSheetDialog>()
    private val adapter = VocabularySpinnerAdapter(vocabularyList)

    override fun init() {
        binding.rvVoca.adapter = adapter
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): DialogBottomSheetVocabularyBinding = DialogBottomSheetVocabularyBinding.inflate(inflater)
}