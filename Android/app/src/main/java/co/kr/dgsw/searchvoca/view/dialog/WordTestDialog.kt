package co.kr.dgsw.searchvoca.view.dialog

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import co.kr.dgsw.searchvoca.R
import co.kr.dgsw.searchvoca.base.BaseDialog
import co.kr.dgsw.searchvoca.databinding.DialogWordTestBinding
import co.kr.dgsw.searchvoca.view.activity.WordCheckActivity
import co.kr.dgsw.searchvoca.viewmodel.activity.CorrectionsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class WordTestDialog : BaseDialog<DialogWordTestBinding>() {
    override val viewModel by viewModel<CorrectionsViewModel>()

    override fun init() {
        setupButton()
    }

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        DialogWordTestBinding.inflate(inflater)

    override fun getDialogSize(): Pair<Int, Int>? {
        val width = resources.getDimensionPixelSize(R.dimen.dialog_word_test_width)
        return Pair(width, WRAP_CONTENT)
    }

    private fun setupButton() {
        binding.btnNext.setOnClickListener {
            val (all, wrong, _) = viewModel.getList()
            val list = if (binding.radioAll.isChecked) all else wrong

            val intent = Intent(requireActivity(), WordCheckActivity::class.java)
                .putExtra("list", list)
            requireActivity().startActivity(intent)
            dismiss()
        }
    }
}