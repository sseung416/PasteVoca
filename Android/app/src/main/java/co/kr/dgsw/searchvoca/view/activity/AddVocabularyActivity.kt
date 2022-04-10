package co.kr.dgsw.searchvoca.view.activity

import android.widget.Toast
import co.kr.dgsw.searchvoca.base.BaseActivity
import co.kr.dgsw.searchvoca.databinding.ActivityAddVocabularyBinding
import co.kr.dgsw.searchvoca.datasource.model.dto.Vocabulary
import co.kr.dgsw.searchvoca.viewmodel.activity.AddVocabularyViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddVocabularyActivity : BaseActivity<ActivityAddVocabularyBinding, AddVocabularyViewModel>() {
    override val viewModel by viewModel<AddVocabularyViewModel>()

    override fun init() {
        binding.layoutAddVocabulary.apply {
            btnBack.setOnClickListener {
                finish()
            }

            btnConfirm.setOnClickListener {
                val msg = getBlankMessage()

                if (msg != null) {
                    Toast.makeText(this@AddVocabularyActivity, msg, Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.insertVocabulary(Vocabulary(name = et1Add.text.toString()))
                    finish()
                }
            }

            et3Add.setOnTouchListener { _, _ ->

                return@setOnTouchListener true
            }
        }
    }

    override fun observeViewModel() {}

    private fun getBlankMessage() =
        with (binding.layoutAddVocabulary) {
            when {
                et1Add.text.isNullOrBlank() -> "단어장명을 입력해주세요."
                else -> null
            }
        }
}