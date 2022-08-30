package co.kr.dgsw.searchvoca.ui.dialog.vocabulary

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import co.kr.dgsw.searchvoca.R
import co.kr.dgsw.searchvoca.databinding.DialogBottomSheetVocabularyBinding
import co.kr.dgsw.searchvoca.ui.bind
import co.kr.dgsw.searchvoca.ui.dialog.BaseBottomSheetDialog
import co.kr.dgsw.searchvoca.ui.word.UpdateTabType
import co.kr.dgsw.searchvoca.ui.word.UpdateWordFragmentDirections
import co.kr.searchvoca.domain.model.Vocabulary
import org.koin.androidx.viewmodel.ext.android.viewModel

class VocabularyBottomSheetDialog(
    private val onItemClicked: (Vocabulary) -> Unit,
    private val addItemVisible: Boolean = false
) : BaseBottomSheetDialog<DialogBottomSheetVocabularyBinding>(R.layout.dialog_bottom_sheet_vocabulary) {

    private val viewModel by viewModel<VocabularyBottomSheetViewModel>()

    private val adapter: VocabularyListAdapter by lazy {
        VocabularyListAdapter({
            onItemClicked(it)
            dismiss()
        }, this::navigateCreateVocabulary)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bind {
            adapter = this@VocabularyBottomSheetDialog.adapter
            vm = viewModel
        }
    }

    private fun navigateCreateVocabulary() =
        if (addItemVisible) {
            findNavController().navigate(
                UpdateWordFragmentDirections.actionUpdateVocabulary(
                    UpdateTabType.CREATE
                )
            )
            dismiss()
        } else null
}