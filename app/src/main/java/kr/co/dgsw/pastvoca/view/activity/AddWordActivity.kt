package kr.co.dgsw.pastvoca.view.activity

import android.widget.Toast
import kr.co.dgsw.pastvoca.base.BaseActivity
import kr.co.dgsw.pastvoca.databinding.ActivityAddWordBinding
import kr.co.dgsw.pastvoca.repository.model.dto.Word
import kr.co.dgsw.pastvoca.viewmodel.activity.AddWordViewModel
import kr.co.dgsw.pastvoca.widget.extension.startActivityWithFinish
import kr.co.dgsw.pastvoca.widget.extension.startActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddWordActivity : BaseActivity<ActivityAddWordBinding, AddWordViewModel>() {
    override val viewModel by viewModel<AddWordViewModel>()

    override fun init() {
        binding.layoutAddWord.apply {
            btnBack.setOnClickListener {
                this@AddWordActivity.startActivityWithFinish(MainActivity::class.java)
            }

            btnConfirm.setOnClickListener {
                val msg = getBlankMessage()
                if (msg != null) {
                    Toast.makeText(this@AddWordActivity, msg, Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.insertWord(
                        Word(
                            vocabularyId = 1,
                            word = et1Add.text.toString(),
                            meaning = et2Add.text.toString()
                        )
                    )
                    finish()
                }
            }

            et3Add.setOnClickListener {
                startActivity(VocabularyActivity::class.java)
            }
        }
    }

    override fun observeViewModel() {
    }

    private fun getBlankMessage() =
        with (binding.layoutAddWord) {
            when {
                et1Add.text.isNullOrBlank() -> "단어를 입력해주세요."
                et2Add.text.isNullOrBlank() -> "뜻을 입력해주세요."
                else -> null
            }
        }
}