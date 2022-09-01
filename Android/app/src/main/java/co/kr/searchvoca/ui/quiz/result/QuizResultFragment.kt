package co.kr.searchvoca.ui.quiz.result

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import co.kr.searchvoca.R
import co.kr.searchvoca.databinding.FragmentQuizResultBinding
import co.kr.searchvoca.ui.BindingFragment
import co.kr.searchvoca.ui.bind

class QuizResultFragment :
    BindingFragment<FragmentQuizResultBinding>(R.layout.fragment_quiz_result) {

    private val navArgs by navArgs<QuizResultFragmentArgs>()
    private val navController by lazy { findNavController() }

    private val adapter by lazy { QuizResultAdapter(navArgs.wordList.toList()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bind {
            adapter = this@QuizResultFragment.adapter
        }

        setupToolbar()
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            navController.navigate(QuizResultFragmentDirections.actionToQuizFragment())
        }
    }
}