package co.kr.dgsw.searchvoca.ui.quiz.listening

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import co.kr.dgsw.searchvoca.R
import co.kr.dgsw.searchvoca.databinding.FragmentListeningTestBinding
import co.kr.dgsw.searchvoca.ui.BindingFragment
import co.kr.dgsw.searchvoca.ui.bind
import co.kr.dgsw.searchvoca.ui.launchAndRepeatWithViewLifecycle
import co.kr.dgsw.searchvoca.ui.quiz.listening.ListeningTestNavigationAction.*
import co.kr.searchvoca.shared.android.extension.showQuitTestDialog
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class ListeningTestFragment :
    BindingFragment<FragmentListeningTestBinding>(R.layout.fragment_listening_test) {

    private val viewModel by viewModel<ListeningTestViewModel>()

    private val navController by lazy { findNavController() }
    private val navArgs by navArgs<ListeningTestFragmentArgs>()

    private val wordList by lazy { navArgs.wordList }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bind {
            vm = viewModel
        }

        binding.etAnswer.requestFocus()
        viewModel.setWordListSize(wordList.size)

        observeState()
        setupToolbar()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.tts.destroy()
    }

    private fun observeState() {
        launchAndRepeatWithViewLifecycle {
            viewModel.navigationAction.collect {
                when (it) {
                    is ShowToastAction ->
                        Toast.makeText(requireContext(), getString(it.msgResId), Toast.LENGTH_LONG)
                            .show()

                    is NavigateToQuizResultAction ->
                        navigateToQuizResult(it.result)

                    is ShowNextWordAction ->
                        viewModel.currentWord.value = wordList[it.idx]
                }
            }

            viewModel.currentWord.collect {
                binding.tts.speak()
            }
        }
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            showQuitTestDialog { navController.popBackStack() }
        }
    }

    private fun navigateToQuizResult(list: List<Boolean>) {
        list.forEachIndexed { idx, result ->
            wordList[idx].isCorrect = result
        }

        navController.navigate(
            ListeningTestFragmentDirections.actionToQuizResultFragment(wordList)
        )
    }
}