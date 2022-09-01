package co.kr.searchvoca.ui.home

import android.os.Bundle
import android.view.View
import co.kr.searchvoca.R
import co.kr.searchvoca.ui.dialog.BaseBottomSheetDialog
import co.kr.searchvoca.databinding.DialogBottomSheetWordBinding
import co.kr.searchvoca.shared.android.extension.setOnClickListenerThrottled

class WordSettingDialog(
    private val onClickEdit: () -> Unit,
    private val onClickDelete: () -> Unit
) : BaseBottomSheetDialog<DialogBottomSheetWordBinding>(R.layout.dialog_bottom_sheet_word) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvEdit.setOnClickListenerThrottled {
            onClickEdit()
            dismiss()
        }

        binding.tvDelete.setOnClickListenerThrottled {
            onClickDelete()
            dismiss()
        }
    }
}