package co.kr.dgsw.searchvoca.view.dialog

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment
import co.kr.dgsw.searchvoca.R
import co.kr.dgsw.searchvoca.databinding.DialogWordTestSettingBinding
import co.kr.dgsw.searchvoca.datasource.model.dto.VocabularyName
import co.kr.dgsw.searchvoca.view.activity.VocabularyActivity
import co.kr.dgsw.searchvoca.view.activity.WordCheckActivity

class WordTestSettingDialog : DialogFragment() {
    private lateinit var binding: DialogWordTestSettingBinding
    private var vocabulary: VocabularyName? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogWordTestSettingBinding.inflate(inflater)
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val getResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == RESULT_OK) {
                    vocabulary = it.data?.getSerializableExtra("vocabulary") as? VocabularyName
                }
            }

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

    override fun onResume() {
        super.onResume()
        val width = resources.getDimensionPixelSize(R.dimen.dialog_word_test_setting_width)
        dialog?.window?.setLayout(width, WRAP_CONTENT)
    }
}