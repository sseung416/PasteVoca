package co.kr.dgsw.searchvoca.view.dialog

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val getResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == RESULT_OK) {
                    vocabulary = it.data?.getSerializableExtra("vocabulary") as? VocabularyName
                }
            }

        binding.button.setOnClickListener {
            val intent = Intent(requireActivity(), VocabularyActivity::class.java)
            getResult.launch(intent)
        }

        binding.button2.setOnClickListener {
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