package co.kr.dgsw.searchvoca.view.dialog

import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import co.kr.dgsw.searchvoca.base.BaseBottomSheetDialog
import co.kr.dgsw.searchvoca.databinding.DialogBottomSheetDefaultBinding
import co.kr.dgsw.searchvoca.widget.view.adapter.DefaultBottomSheetAdapter

class DefaultBottomSheetDialog(
    list: List<Pair<Int?, String>>,
    private val callback: (Pair<Int?, String>) -> Unit,
    private val title: String? = null
    ) : BaseBottomSheetDialog<DialogBottomSheetDefaultBinding>() {
    private val adapter = DefaultBottomSheetAdapter(list)

    override fun init() {
        binding.tvTitle.apply {
            if (title == null) {
                visibility = GONE
            } else {
                visibility = VISIBLE
                text = title
            }
        }

        binding.rvDefault.adapter = adapter.apply {
            onClickItem = {
                callback.invoke(it)
                dismiss()
            }
        }
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = DialogBottomSheetDefaultBinding.inflate(inflater)
}