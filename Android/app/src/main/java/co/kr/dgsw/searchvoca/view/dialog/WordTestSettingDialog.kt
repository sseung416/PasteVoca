package co.kr.dgsw.searchvoca.view.dialog

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import co.kr.dgsw.searchvoca.R
import co.kr.dgsw.searchvoca.base.BaseDialog
import co.kr.dgsw.searchvoca.databinding.DialogWordTestSettingBinding
import co.kr.dgsw.searchvoca.datasource.model.dto.VocabularyName
import co.kr.dgsw.searchvoca.view.activity.VocabularyActivity
import co.kr.dgsw.searchvoca.view.activity.WordCheckActivity

class WordTestSettingDialog : BaseDialog<DialogWordTestSettingBinding>() {
    private var vocabulary: VocabularyName? = null
    private val getResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK)
                vocabulary = it.data?.getSerializableExtra("vocabulary") as? VocabularyName
        }

    override fun init() {
        setupButton()
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): DialogWordTestSettingBinding =
        DialogWordTestSettingBinding.inflate(inflater, container, false)

    override fun getDialogSize(): Pair<Int, Int>? {
        val width = resources.getDimensionPixelSize(R.dimen.dialog_word_test_setting_width)
        return Pair(width, WRAP_CONTENT)
    }

    private fun setupButton() {
        binding.etVocabulary.setOnTouchListener { _, _ ->
            val intent = Intent(requireActivity(), VocabularyActivity::class.java)
            getResult.launch(intent)
            return@setOnTouchListener true
        }

        binding.btnNext.setOnClickListener {
            if (vocabulary == null) {
                Toast.makeText(requireContext(), "테스트할 단어장을 선택해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(requireContext(), WordCheckActivity::class.java)
                    .putExtra("vocabulary", vocabulary)
                requireActivity().startActivity(intent)
                dismiss()
            }
        }
    }
}