package co.kr.dgsw.searchvoca.view.fragment

import android.view.MenuItem
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import co.kr.dgsw.searchvoca.R
import co.kr.dgsw.searchvoca.base.BaseFragment
import co.kr.dgsw.searchvoca.databinding.FragmentAddVocabularyBinding
import co.kr.dgsw.searchvoca.viewmodel.fragment.UpdateVocabularyViewModel
import co.kr.dgsw.searchvoca.widget.livedata.EventObserver
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddVocabularyFragment : BaseFragment<FragmentAddVocabularyBinding, UpdateVocabularyViewModel>() {
    override val viewModel by viewModel<UpdateVocabularyViewModel>()

    private val navController by lazy { findNavController() }

    override fun init() {
        setHasOptionsMenu(true)
    }

    override fun observeViewModel() {
        viewModel.errorMessage.observe(viewLifecycleOwner, EventObserver {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })

        viewModel.insertEvent.observe(viewLifecycleOwner) {
            navController.popBackStack()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> navController.popBackStack()
            R.id.toolbar_confirm_add -> viewModel.insertVocabulary()
        }
        return true
    }
}