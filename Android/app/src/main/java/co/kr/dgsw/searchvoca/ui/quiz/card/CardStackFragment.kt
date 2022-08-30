package co.kr.dgsw.searchvoca.ui.quiz.card

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import co.kr.dgsw.searchvoca.R
import co.kr.dgsw.searchvoca.databinding.FragmentCardTestBinding
import co.kr.dgsw.searchvoca.ui.BindingFragment
import co.kr.dgsw.searchvoca.ui.bind
import co.kr.dgsw.searchvoca.ui.quiz.showQuitTestDialog
import co.kr.dgsw.searchvoca.widget.view.CardStackAdapter
import kr.co.dgsw.cardstackview.CardStackLayoutManager
import kr.co.dgsw.cardstackview.Direction
import kr.co.dgsw.cardstackview.StackFrom

class CardStackFragment
    : BindingFragment<FragmentCardTestBinding>(R.layout.fragment_card_test), CardStackAdapter {

    private val navController by lazy { findNavController() }
    private val navArgs by navArgs<CardStackFragmentArgs>()

    private val adapter by lazy {
        WordCardStackAdapter(navArgs.wordList.toList())
    }
    private val manager by lazy {
        CardStackLayoutManager(requireContext(), this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        manager.setStackFrom(StackFrom.Top)

        binding.bind {
            this.adapter = adapter
            this.manager = manager
        }

        setupToolbar()
    }

    override fun onCardSwiped(direction: Direction?) {
        // 왼쪽 스와이프: 정답, 오른쪽 스와이프: 오답
        Log.e("CardStackView", "onCardSwiped: $direction, ${manager.topPosition}")
        adapter.setCorrect(direction == Direction.Left)

        if (adapter.itemCount == manager.topPosition) {
            navigateToQuizResult()
        }
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            showQuitTestDialog {
                navController.popBackStack()
            }
        }
    }

    private fun navigateToQuizResult() {
        val result = adapter.getList().toTypedArray()
        navController.navigate(CardStackFragmentDirections.actionToQuizResultFragment(result))
    }
}