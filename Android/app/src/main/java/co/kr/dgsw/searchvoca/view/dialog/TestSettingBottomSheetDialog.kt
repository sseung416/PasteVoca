package co.kr.dgsw.searchvoca.view.dialog

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import co.kr.dgsw.searchvoca.base.BaseBottomSheetDialog
import co.kr.dgsw.searchvoca.databinding.DialogBottomSheetTestSettingBinding
import co.kr.dgsw.searchvoca.datasource.model.dto.VocabularyName
import co.kr.dgsw.searchvoca.view.activity.CardTestActivity
import co.kr.dgsw.searchvoca.viewmodel.dialog.TestSettingViewModel
import co.kr.dgsw.searchvoca.widget.extension.setOnClickListenerThrottled
import co.kr.dgsw.searchvoca.widget.view.adapter.VocabularyBottomSheetAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class TestSettingBottomSheetDialog(
    private val vocabularyNameList: List<VocabularyName>
) : BaseBottomSheetDialog<DialogBottomSheetTestSettingBinding>() {
    override val viewModel by viewModel<TestSettingViewModel>()

    override fun init() {
        setupBinding()
        setupRecyclerView()
        setupButton()
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = DialogBottomSheetTestSettingBinding.inflate(inflater)

    private fun setupBinding() {
        binding.vm = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.executePendingBindings()
    }

    private fun setupRecyclerView() {
        binding.rvVoca.adapter = VocabularyBottomSheetAdapter().apply {
            setList(vocabularyNameList)
            onClickItemListener = {
                viewModel.select(it)
            }
        }
    }

    private fun setupButton() {
        binding.btnCancel.setOnClickListenerThrottled {
            dismiss()
        }

        binding.btnConfirm.setOnClickListenerThrottled {
            moveToWordCheck()
            dismiss()
        }
    }

    private fun moveToWordCheck() {
        val intent = Intent(requireContext(), CardTestActivity::class.java)
            .putExtra("vocabulary", viewModel.selectVocabularyName.value)
            .putExtra("problemCount", viewModel.problemCount.value?.toInt())
        requireActivity().startActivity(intent)
    }
}