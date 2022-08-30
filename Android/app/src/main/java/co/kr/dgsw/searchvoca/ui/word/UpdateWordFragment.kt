package co.kr.dgsw.searchvoca.ui.word

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import co.kr.dgsw.searchvoca.R
import co.kr.dgsw.searchvoca.databinding.FragmentUpdateWordBinding
import co.kr.dgsw.searchvoca.ui.BindingFragment
import co.kr.dgsw.searchvoca.ui.bind
import co.kr.dgsw.searchvoca.ui.launchAndRepeatWithViewLifecycle
import co.kr.dgsw.searchvoca.ui.word.UpdateWordNavigationAction.*
import co.kr.dgsw.searchvoca.ui.dialog.DefaultBottomSheetDialog
import co.kr.dgsw.searchvoca.ui.dialog.VocabularyBottomSheetDialog
import co.kr.searchvoca.domain.model.Definition
import co.kr.searchvoca.domain.model.Word
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class UpdateWordFragment :
    BindingFragment<FragmentUpdateWordBinding>(R.layout.fragment_update_word) {

    private val viewModel by viewModel<UpdateWordViewModel>()

    private val navController by lazy { findNavController() }
    private val args by navArgs<UpdateWordFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.setTabType(args.type)
        setWord(args.word)

        binding.bind {
            vm = viewModel
        }

        observeState()
        setupToolbar()
    }

    private fun observeState() {
        launchAndRepeatWithViewLifecycle {
            viewModel.navigateActions.collect { action ->
                when (action) {
                    ShowVocabularyAction -> showVocabularyDialog()

                    BackAction -> navController.popBackStack()
                }
            }

            viewModel.searchResult.collect {
                showSearchResultDialog(it!!)
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
                    viewModel.updateWord()
                    true
                }

                else -> false
            }
        }

        binding.toolbar.setNavigationOnClickListener {
            navController.popBackStack()
        }
    }

    private fun showVocabularyDialog() {
        VocabularyBottomSheetDialog(
            {
                viewModel.vocabulary.value = it
            }, true
        ).show(parentFragmentManager, "")
    }

    private fun showSearchResultDialog(definitions: List<Definition>) {
        val list = definitions.map { Pair(null, it.definition) }
        DefaultBottomSheetDialog(
            list,
            { viewModel.definition.value = it.second },
            getString(R.string.title_choice_definition)
        ).show(parentFragmentManager, "")
    }

    private fun setWord(word: Word?) {
        word?.let {
            viewModel.word.value = it.word
            viewModel.definition.value = it.definition
            viewModel.loadVocabulary(it.vocabularyId)
        }
    }
}