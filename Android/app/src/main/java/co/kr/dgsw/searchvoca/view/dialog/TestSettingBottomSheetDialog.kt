package co.kr.dgsw.searchvoca.view.dialog

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import co.kr.dgsw.searchvoca.base.BaseBottomSheetDialog
import co.kr.dgsw.searchvoca.databinding.DialogBottomSheetTestSettingBinding
import co.kr.dgsw.searchvoca.view.data.TestWord
import co.kr.dgsw.searchvoca.datasource.model.dto.VocabularyName
import co.kr.dgsw.searchvoca.datasource.model.dto.Word
import co.kr.dgsw.searchvoca.viewmodel.dialog.TestSettingViewModel
import co.kr.dgsw.searchvoca.widget.livedata.EventObserver
import co.kr.dgsw.searchvoca.widget.view.adapter.VocabularyBottomSheetAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class TestSettingBottomSheetDialog<T>(
    private val vocabularyNameList: List<VocabularyName>,
    private val navigateActivity: Class<T>
) : BaseBottomSheetDialog<DialogBottomSheetTestSettingBinding>() {
    override val viewModel by viewModel<TestSettingViewModel>()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = DialogBottomSheetTestSettingBinding.inflate(inflater)

    override fun init() {
        setupBinding()
        setupRecyclerView()
    }

    override fun observeViewModel() {
        viewModel.apply {
            wordList.observe(viewLifecycleOwner, EventObserver {
                val count = if (problemCount.value.isNullOrBlank()) {
                    it.size
                } else {
                    problemCount.value!!.toInt()
                }

                navigateToActivity(ArrayList(it.format(count)))
                dismiss()
            })

            clickEventCancel.observe(viewLifecycleOwner, EventObserver {
                dismiss()
            })

            viewModel.errorMessage.observe(viewLifecycleOwner, EventObserver {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            })
        }
    }

    private fun setupBinding() {
        binding.vm = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.executePendingBindings()
    }

    private fun setupRecyclerView() {
        binding.rvVoca.adapter = VocabularyBottomSheetAdapter().apply {
            setList(vocabularyNameList)
            onClickItemListener = {
                viewModel.selectVocabulary(it)
            }
        }
    }

    private fun navigateToActivity(list: ArrayList<TestWord>) {
        val intent = Intent(requireContext(), navigateActivity)
            .putParcelableArrayListExtra("list", list)
        requireActivity().startActivity(intent)
    }

    private fun List<Word>.format(count: Int) =
        this.map { TestWord(it.word, it.meaning) }.shuffled().slice(0 until count)
}