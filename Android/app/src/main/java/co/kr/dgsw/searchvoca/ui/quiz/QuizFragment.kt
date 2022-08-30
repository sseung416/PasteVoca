package co.kr.dgsw.searchvoca.ui.quiz

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import co.kr.dgsw.searchvoca.R
import co.kr.dgsw.searchvoca.databinding.FragmentQuizBinding
import co.kr.dgsw.searchvoca.ui.BindingFragment
import co.kr.dgsw.searchvoca.ui.bind
import co.kr.dgsw.searchvoca.ui.launchAndRepeatWithViewLifecycle
import co.kr.dgsw.searchvoca.ui.quiz.QuizNavigationAction.*
import co.kr.dgsw.searchvoca.ui.quiz.setting.QuizSettingDialog
import co.kr.searchvoca.domain.model.successOrNull
import org.koin.androidx.viewmodel.ext.android.viewModel

class QuizFragment : BindingFragment<FragmentQuizBinding>(R.layout.fragment_quiz) {

    private val viewModel by viewModel<QuizViewModel>()

    private val navController by lazy { findNavController() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bind {
            vm = viewModel
        }

        observeState()
    }

    private fun observeState() {
        launchAndRepeatWithViewLifecycle {
            viewModel.navigationAction.collect { action ->
                when (action) {
                    NavigateToCardTestAction -> showSettingBottomSheetDialog { list ->
                        navController.navigate(QuizFragmentDirections.actionToCardStackFragment(list))
                    }

                    NavigateToListeningTestAction -> showSettingBottomSheetDialog { list ->
                        navController.navigate(QuizFragmentDirections.actionToListeningTest(list))
                    }
                }
            }
        }
    }

    private fun showSettingBottomSheetDialog(onConfirmClicked: (Array<TestWord>) -> Unit) {
        QuizSettingDialog(onConfirmClicked).show(parentFragmentManager, "")
    }
}