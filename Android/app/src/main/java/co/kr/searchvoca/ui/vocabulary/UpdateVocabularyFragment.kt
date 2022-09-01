package co.kr.searchvoca.ui.vocabulary

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import co.kr.searchvoca.R
import co.kr.searchvoca.databinding.FragmentUpdateVocabularyBinding
import co.kr.searchvoca.ui.BindingFragment
import co.kr.searchvoca.ui.bind
import co.kr.searchvoca.ui.launchAndRepeatWithViewLifecycle
import co.kr.searchvoca.ui.vocabulary.UpdateVocabularyAction.BackAction
import org.koin.androidx.viewmodel.ext.android.viewModel

class UpdateVocabularyFragment : BindingFragment<FragmentUpdateVocabularyBinding>(R.layout.fragment_update_vocabulary) {

    private val viewModel by viewModel<UpdateVocabularyViewModel>()

    private val navController by lazy { findNavController() }
    private val args by navArgs<UpdateVocabularyFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.setTabType(args.type)

        binding.bind {
            vm = viewModel
        }

        observeState()
        setupToolbar()
    }

    private fun observeState() {
        launchAndRepeatWithViewLifecycle {
            viewModel.navigateAction.collect {
                when (it) {
                    BackAction -> navController.popBackStack()
                }
            }

            viewModel.error.collect {
                Toast.makeText(requireContext(), getString(it), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupToolbar() {
        binding.toolbar.setOnMenuItemClickListener {
            return@setOnMenuItemClickListener when (it.itemId) {
                R.id.toolbar_confirm_add -> {
                    viewModel.updateVocabulary()
                    true
                }

                else -> false
            }
        }

        binding.toolbar.setNavigationOnClickListener {
            navController.popBackStack()
        }
    }
}