package co.kr.dgsw.searchvoca.ui.dialog

import android.os.Bundle
import android.view.View
import co.kr.dgsw.searchvoca.R
import co.kr.dgsw.searchvoca.databinding.DialogBottomSheetDefaultBinding
import co.kr.dgsw.searchvoca.ui.bind

open class DefaultBottomSheetDialog(
    list: List<Pair<Int?, String>>,
    itemClickEvent: (Pair<Int?, String>) -> Unit,
    private val title: String? = null
) : BaseBottomSheetDialog<DialogBottomSheetDefaultBinding>(R.layout.dialog_bottom_sheet_default) {

    private val adapter by lazy {
        DefaultBottomSheetAdapter(list, itemClickEvent.also { dismiss() })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bind {
            title = this@DefaultBottomSheetDialog.title
            this.adapter = this@DefaultBottomSheetDialog.adapter
        }
    }
}