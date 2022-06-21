package co.kr.dgsw.searchvoca.view.fragment

import android.util.Log
import android.view.MenuItem
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import co.kr.dgsw.searchvoca.base.BaseFragment
import co.kr.dgsw.searchvoca.databinding.FragmentCardTestBinding
import co.kr.dgsw.searchvoca.view.data.TestWord
import co.kr.dgsw.searchvoca.viewmodel.fragment.CardTestViewModel
import co.kr.dgsw.searchvoca.widget.extension.showQuitTestDialog
import co.kr.dgsw.searchvoca.widget.view.CardStackAdapter
import co.kr.dgsw.searchvoca.widget.view.adapter.WordCardStackAdapter
import kr.co.dgsw.cardstackview.CardStackLayoutManager
import kr.co.dgsw.cardstackview.Direction
import kr.co.dgsw.cardstackview.StackFrom

class CardStackFragment : BaseFragment<FragmentCardTestBinding, CardTestViewModel>(), CardStackAdapter {
    override val viewModel by viewModels<CardTestViewModel>()

    private val navController by lazy { findNavController() }

    private val adapter = WordCardStackAdapter()
    private val manager by lazy { CardStackLayoutManager(requireContext(), this) }
    private val list by lazy {
        requireActivity().intent.getParcelableArrayListExtra<TestWord>("list") as List<TestWord>
    }

    override fun init() {
        hasOptionsMenu()
        adapter.setList(list)

        setupCardStackView()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> showQuitTestDialog()
        }
        return true
    }

    override fun onCardSwiped(direction: Direction?) {
        // 왼쪽 스와이프: 정답, 오른쪽 스와이프: 오답
        Log.e("CardStackView", "onCardSwiped: $direction, ${manager.topPosition}")
        adapter.setCorrect(direction == Direction.Left)

        if (adapter.itemCount == manager.topPosition) {
            navigateToCorrections()
        }
    }

    private fun setupCardStackView() {
        manager.setStackFrom(StackFrom.Top)
        binding.stackWordCheck.apply {
            adapter = this@CardStackFragment.adapter
            layoutManager = manager
        }
    }

    private fun navigateToCorrections() {
        val correctionsArray = adapter.getList().toTypedArray()
        navController.navigate(CardStackFragmentDirections.actionToCorrectionsFragment(correctionsArray))
    }
}