package co.kr.dgsw.searchvoca.view.dialog

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import co.kr.dgsw.searchvoca.R
import co.kr.dgsw.searchvoca.base.BaseDialog
import co.kr.dgsw.searchvoca.databinding.DialogBottomSheetWordBinding
import co.kr.dgsw.searchvoca.datasource.model.dto.Word
import co.kr.dgsw.searchvoca.view.activity.AddWordActivity
import co.kr.dgsw.searchvoca.viewmodel.fragment.WordBottomSheetViewModel
import org.koin.android.ext.android.inject

class WordBottomSheetDialog(
    private val word: Word
) : BaseDialog<DialogBottomSheetWordBinding>() {
    override val viewModel by inject<WordBottomSheetViewModel>()

    override fun init() {
        setupButton()
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): DialogBottomSheetWordBinding = DialogBottomSheetWordBinding.inflate(inflater)

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    private fun setupButton() {
        binding.tvEdit.setOnClickListener {
            val intent = Intent(requireActivity(), AddWordActivity::class.java)
                .putExtra("word", word)
            startActivity(intent)
            dismiss()
        }

        binding.tvDelete.setOnClickListener {
            viewModel.deleteWord(word)
            dismiss()
        }
    }

    companion object { const val TAG = "WordBottomSheetDialog" }
}