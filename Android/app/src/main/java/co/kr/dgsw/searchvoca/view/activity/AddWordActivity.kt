package co.kr.dgsw.searchvoca.view.activity

import android.content.Intent
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import co.kr.dgsw.searchvoca.base.BaseActivity
import co.kr.dgsw.searchvoca.databinding.ActivityAddWordBinding
import co.kr.dgsw.searchvoca.repository.model.dto.VocabularyName
import co.kr.dgsw.searchvoca.repository.model.dto.Word
import co.kr.dgsw.searchvoca.viewmodel.activity.AddWordViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddWordActivity : BaseActivity<ActivityAddWordBinding, AddWordViewModel>() {
    override val viewModel by viewModel<AddWordViewModel>()

    private var vocabulary: VocabularyName? = null

    override fun init() {
        val getResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == RESULT_OK) {
                    vocabulary = it.data?.getSerializableExtra("vocabulary") as VocabularyName
                    binding.layoutAddWord.et3Add.setText(vocabulary!!.name)
                }
            }

        binding.layoutAddWord.apply {
            btnBack.setOnClickListener {
                finish()
            }

            btnConfirm.setOnClickListener {
                val msg = getBlankMessage()
                if (msg != null) {
                    Toast.makeText(this@AddWordActivity, msg, Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.insertWord(
                        Word(
                            vocabularyId = vocabulary?.id ?: 1,
                            word = et1Add.text.toString(),
                            meaning = et2Add.text.toString()
                        )
                    )
                    finish()
                }
            }

            et3Add.setOnClickListener {
                getResult.launch(
                    Intent(
                        this@AddWordActivity,
                        VocabularyActivity::class.java
                    ).putExtra("id", vocabulary?.id ?: 1)
                )
            }
        }
    }

    override fun observeViewModel() {}

    private fun getBlankMessage() =
        with (binding.layoutAddWord) {
            when {
                et1Add.text.isNullOrBlank() -> "단어를 입력해주세요."
                et2Add.text.isNullOrBlank() -> "뜻을 입력해주세요."
                else -> null
            }
        }
}