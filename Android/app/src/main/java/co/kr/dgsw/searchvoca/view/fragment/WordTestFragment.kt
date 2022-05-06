package co.kr.dgsw.searchvoca.view.fragment

import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Spinner
import android.widget.TextView
import co.kr.dgsw.searchvoca.R
import co.kr.dgsw.searchvoca.base.BaseFragment
import co.kr.dgsw.searchvoca.databinding.FragmentWordTestBinding
import co.kr.dgsw.searchvoca.view.dialog.WordTestSettingDialog
import co.kr.dgsw.searchvoca.viewmodel.fragment.WordTestViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class WordTestFragment : BaseFragment<FragmentWordTestBinding, WordTestViewModel>() {
    override val viewModel by viewModel<WordTestViewModel>()

    override fun init() {
        setupToolbar()
        setupButton()
    }

    private fun setupToolbar() {
        requireActivity().findViewById<Spinner>(R.id.toolbar_spinner_main).visibility = GONE
        requireActivity().findViewById<TextView>(R.id.toolbar_title_main).apply {
            visibility = VISIBLE
            text = "테스트"
        }
    }

    private fun setupButton() {
        binding.cvWordCard.setOnClickListener {
            WordTestSettingDialog().show(parentFragmentManager, "")
        }

        binding.cvWrongAnswerNote.setOnClickListener {

        }
    }
}