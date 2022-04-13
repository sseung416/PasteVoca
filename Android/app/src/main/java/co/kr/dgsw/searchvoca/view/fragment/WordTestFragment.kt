package co.kr.dgsw.searchvoca.view.fragment

import co.kr.dgsw.searchvoca.base.BaseFragment
import co.kr.dgsw.searchvoca.databinding.FragmentWordTestBinding
import co.kr.dgsw.searchvoca.view.dialog.WordTestSettingDialog
import co.kr.dgsw.searchvoca.viewmodel.fragment.WordTestViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class WordTestFragment : BaseFragment<FragmentWordTestBinding, WordTestViewModel>() {
    override val viewModel by viewModel<WordTestViewModel>()

    override fun init() {
        setupButton()
    }

    private fun setupButton() {
        binding.cvWordCard.setOnClickListener {
            WordTestSettingDialog().show(parentFragmentManager, "")
        }

        binding.cvWrongAnswerNote.setOnClickListener {

        }
    }
}