package co.kr.dgsw.searchvoca.view.fragment

import android.view.MenuItem
import androidx.navigation.fragment.navArgs
import co.kr.dgsw.searchvoca.base.BaseFragment
import co.kr.dgsw.searchvoca.databinding.FragmentCorrectionsBinding
import co.kr.dgsw.searchvoca.viewmodel.fragment.CorrectionsViewModel
import co.kr.dgsw.searchvoca.widget.view.adapter.CorrectionsAdapter
import org.koin.android.ext.android.inject

class CorrectionsFragment : BaseFragment<FragmentCorrectionsBinding, CorrectionsViewModel>() {
    override val viewModel by inject<CorrectionsViewModel>()
    private val adapter = CorrectionsAdapter()

    private val navArgs by navArgs<CorrectionsFragmentArgs>()

    override fun init() {
        hasOptionsMenu()
        setupRecyclerView()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> requireActivity().finish()
        }
        return true
    }

    private fun setupRecyclerView() {
        binding.rvCorrections.adapter = adapter
        adapter.setList(navArgs.correctionsList.toList())
    }
}