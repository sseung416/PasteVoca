package co.kr.dgsw.searchvoca.view.fragment

import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.LinearLayout
import android.widget.TextView
import co.kr.dgsw.searchvoca.R
import co.kr.dgsw.searchvoca.base.BaseFragment
import co.kr.dgsw.searchvoca.databinding.FragmentWordTestBinding
import co.kr.dgsw.searchvoca.view.activity.CardTestActivity
import co.kr.dgsw.searchvoca.view.activity.ListeningTestActivity
import co.kr.dgsw.searchvoca.view.dialog.TestSettingBottomSheetDialog
import co.kr.dgsw.searchvoca.viewmodel.fragment.WordTestViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class WordTestFragment : BaseFragment<FragmentWordTestBinding, WordTestViewModel>() {
    override val viewModel by viewModel<WordTestViewModel>()

    override fun init() {
        viewModel.getVocabularyNameList()
        setupToolbar()
    }

    override fun observeViewModel() {
        viewModel.apply {
            cardStackClickEvent.observe(viewLifecycleOwner) {
                showSettingBottomSheetDialog(CardTestActivity::class.java)
            }

            listeningTestClickEvent.observe(viewLifecycleOwner) {
                showSettingBottomSheetDialog(ListeningTestActivity::class.java)
            }
        }
    }

    private fun setupToolbar() {
        requireActivity().findViewById<LinearLayout>(R.id.toolbar_spinner_main).visibility = GONE
        requireActivity().findViewById<TextView>(R.id.toolbar_title_main).apply {
            visibility = VISIBLE
            text = "테스트"
        }
    }

    private fun showSettingBottomSheetDialog(toActivity: Class<*>) {
        TestSettingBottomSheetDialog(viewModel.vocabularyList, toActivity).show(parentFragmentManager, "")
    }
}