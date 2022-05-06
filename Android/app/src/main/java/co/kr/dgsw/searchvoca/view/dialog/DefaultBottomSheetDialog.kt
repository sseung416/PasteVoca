package co.kr.dgsw.searchvoca.view.dialog

import android.view.LayoutInflater
import android.view.ViewGroup
import co.kr.dgsw.searchvoca.base.BaseBottomSheetDialog
import co.kr.dgsw.searchvoca.databinding.DialogBottomSheetDefaultBinding
import co.kr.dgsw.searchvoca.viewmodel.dialog.DefaultBottomSheetViewModel
import co.kr.dgsw.searchvoca.widget.livedata.Event
import co.kr.dgsw.searchvoca.widget.view.adapter.DefaultBottomSheetAdapter
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class DefaultBottomSheetDialog(
    list: List<Pair<Int?, String>>,
    private val callback: (Pair<Int?, String>) -> Unit
) : BaseBottomSheetDialog<DialogBottomSheetDefaultBinding>() {

    override val viewModel by sharedViewModel<DefaultBottomSheetViewModel>()
    private val adapter = DefaultBottomSheetAdapter(list)

    override fun init() {
        viewModel.callback = callback

        binding.rvDefault.adapter = adapter.apply {
            onClickItem = {
                viewModel.clickedItem.value = Event(it)
                dismiss()
            }
        }
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = DialogBottomSheetDefaultBinding.inflate(inflater)
}