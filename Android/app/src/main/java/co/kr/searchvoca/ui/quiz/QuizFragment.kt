package co.kr.searchvoca.ui.quiz

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import co.kr.searchvoca.R
import co.kr.searchvoca.databinding.FragmentQuizBinding
import co.kr.searchvoca.ui.BindingFragment
import co.kr.searchvoca.ui.bind
import co.kr.searchvoca.ui.launchAndRepeatWithViewLifecycle
import co.kr.searchvoca.ui.quiz.QuizNavigationAction.*
import co.kr.searchvoca.ui.quiz.setting.QuizSettingDialog
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