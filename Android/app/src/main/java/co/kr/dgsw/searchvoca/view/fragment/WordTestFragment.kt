package co.kr.dgsw.searchvoca.view.fragment

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import co.kr.dgsw.searchvoca.base.BaseFragment
import co.kr.dgsw.searchvoca.databinding.FragmentWordTestBinding
import co.kr.dgsw.searchvoca.repository.model.dto.VocabularyName
import co.kr.dgsw.searchvoca.view.activity.VocabularyActivity
import co.kr.dgsw.searchvoca.view.activity.WordCheckActivity
import co.kr.dgsw.searchvoca.viewmodel.fragment.WordTestViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class WordTestFragment : BaseFragment<FragmentWordTestBinding, WordTestViewModel>() {
    override val viewModel by viewModel<WordTestViewModel>()
    private var vocabulary: VocabularyName? = null

    override fun init() {

        val getResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == RESULT_OK) {
                    vocabulary = it.data?.getSerializableExtra("vocabulary") as? VocabularyName
                    binding.et.setText(vocabulary?.name ?: "전체")
                }
            }

        binding.button.setOnClickListener {
            getResult.launch(Intent(requireActivity(), VocabularyActivity::class.java))
        }

        binding.button2.setOnClickListener {
            if (vocabulary == null) {
                Toast.makeText(requireContext(), "테스트할 단어장을 선택해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(requireContext(), WordCheckActivity::class.java)
                    .putExtra("vocabulary", vocabulary)
                requireActivity().startActivity(intent)
            }
        }
    }

    override fun observeViewModel() {}
}