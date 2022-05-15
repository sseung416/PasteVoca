package co.kr.dgsw.searchvoca.view.fragment

import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Spinner
import android.widget.TextView
import co.kr.dgsw.searchvoca.R
import co.kr.dgsw.searchvoca.base.BaseFragment
import co.kr.dgsw.searchvoca.databinding.FragmentWordTestBinding
import co.kr.dgsw.searchvoca.view.activity.CorrectionsListActivity
import co.kr.dgsw.searchvoca.view.dialog.TestSettingBottomSheetDialog
import co.kr.dgsw.searchvoca.viewmodel.fragment.WordTestViewModel
import co.kr.dgsw.searchvoca.widget.extension.setOnClickListenerThrottled
import org.koin.androidx.viewmodel.ext.android.viewModel
import co.kr.dgsw.searchvoca.widget.extension.startActivity

class WordTestFragment : BaseFragment<FragmentWordTestBinding, WordTestViewModel>() {
    override val viewModel by viewModel<WordTestViewModel>()

    override fun init() {
        viewModel.getVocabularyNameList()
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
        binding.cvWordCard.setOnClickListenerThrottled {
            val list = viewModel.vocabularyNameList.value?.peekContent() ?: listOf()
            TestSettingBottomSheetDialog(list).show(parentFragmentManager, "")
        }

        binding.cvWrongAnswerNote.setOnClickListener {
            requireActivity().startActivity(CorrectionsListActivity::class.java)
        }
    }
}