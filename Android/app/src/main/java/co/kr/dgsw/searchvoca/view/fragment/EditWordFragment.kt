package co.kr.dgsw.searchvoca.view.fragment

import android.view.MenuItem
import android.widget.Toast
import co.kr.dgsw.searchvoca.R
import co.kr.dgsw.searchvoca.base.BaseFragment
import co.kr.dgsw.searchvoca.databinding.FragmentEditWordBinding
import co.kr.dgsw.searchvoca.datasource.model.dto.Word
import co.kr.dgsw.searchvoca.view.dialog.DefaultBottomSheetDialog
import co.kr.dgsw.searchvoca.view.dialog.VocabularyBottomSheetDialog
import co.kr.dgsw.searchvoca.viewmodel.fragment.UpdateWordViewModel
import co.kr.dgsw.searchvoca.widget.livedata.EventObserver
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditWordFragment : BaseFragment<FragmentEditWordBinding, UpdateWordViewModel>() {
    override val viewModel by viewModel<UpdateWordViewModel>()
    private val data by lazy {
        requireActivity().intent.getSerializableExtra("word") as Word
    }

    override fun init() {
        viewModel.apply {
            this.word.value = data.word
            meaning.value = data.meaning
            getVocabularyNameById(data.vocabularyId)
        }
    }

    override fun observeViewModel() {
        viewModel.apply {
            vocabularyTouchEvent.observe(viewLifecycleOwner) {
                VocabularyBottomSheetDialog().show(parentFragmentManager, "")
            }

            definitionList.observe(viewLifecycleOwner, EventObserver { list ->
                DefaultBottomSheetDialog(list.map { Pair(null, it) }, {
                    viewModel.meaning.value = it.second
                }, "뜻을 선택해주세요.").show(parentFragmentManager, "")
            })

            updateEvent.observe(viewLifecycleOwner) {
                requireActivity().finish()
            }

            errorMessage.observe(viewLifecycleOwner, EventObserver {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            })
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> requireActivity().finish()
            R.id.toolbar_confirm_add -> viewModel.updateWord(data.id!!)
        }
        return true
    }
}