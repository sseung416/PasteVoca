package co.kr.dgsw.searchvoca.view.activity

import android.content.Intent
import android.view.MotionEvent
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import co.kr.dgsw.searchvoca.base.BaseActivity
import co.kr.dgsw.searchvoca.databinding.ActivityAddWordBinding
import co.kr.dgsw.searchvoca.datasource.model.dto.VocabularyName
import co.kr.dgsw.searchvoca.datasource.model.dto.Word
import co.kr.dgsw.searchvoca.view.dialog.SearchResultDialog
import co.kr.dgsw.searchvoca.viewmodel.activity.AddWordViewModel
import co.kr.dgsw.searchvoca.widget.livedata.EventObserver
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddWordActivity : BaseActivity<ActivityAddWordBinding, AddWordViewModel>(),
    SearchResultDialog.OnWordSelectedListener {

    private val getResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                vocabulary = it.data?.getSerializableExtra("vocabulary") as VocabularyName
                binding.layoutAddWord.et3Add.setText(vocabulary!!.name)
            }
        }

    override val viewModel by viewModel<AddWordViewModel>()
    private var vocabulary: VocabularyName? = null

    override fun init() {
        val intentData = intent.getSerializableExtra("word") as? Word
        setupData(intentData)
        setupLayout(intentData)
    }

    override fun observeViewModel() {
        viewModel.apply {
            searchData.observe(this@AddWordActivity, EventObserver {
                val list = it ?: listOf()
                if (list.size == 1) {
                    binding.layoutAddWord.et2Add.setText(list[0].definition)
                } else {
                    SearchResultDialog(list.map { item -> item.definition })
                        .show(supportFragmentManager, "TAG")
                }
            })
        }
    }

    private fun setupLayout(data: Word?) {
        binding.layoutAddWord.apply {
            btnBack.setOnClickListener {
                finish()
            }

            btnConfirm.setOnClickListener {
                val msg = getBlankMessage()
                if (msg != null) {
                    Toast.makeText(this@AddWordActivity, msg, Toast.LENGTH_SHORT).show()
                } else {
                    val word = Word(vocabulary?.id ?: 1, et1Add.text.toString(), et2Add.text.toString())
                    if (isInsertType()) {
                        viewModel.insertWord(word)
                    } else {
                        word.id = data!!.id
                        viewModel.updateWord(word)
                    }
                    finish()
                }
            }

            et1Add.setOnTouchListener { v, motionEvent ->
                v as EditText

                if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                    if (v.text.isNotBlank()) {
                        if (motionEvent.rawX >= (v.width - v.compoundDrawables[2].bounds.width())) {
                            viewModel.getSearchData(et1Add.text.toString())
                            return@setOnTouchListener true
                        }
                    }
                }
                return@setOnTouchListener false
            }

            et3Add.apply {
                setText("미정")
                setOnTouchListener { _, _ ->
                    getResult.launch(
                        Intent(this@AddWordActivity, VocabularyActivity::class.java)
                            .putExtra("id", vocabulary?.id ?: 1)
                    )
                    return@setOnTouchListener true
                }
            }
        }
    }

    private fun setupData(word: Word?) {
        if (word != null) {
            binding.layoutAddWord.apply {
                et1Add.setText(word.word)
                et2Add.setText(word.meaning)
            }
        }
    }

    private fun getBlankMessage() =
        with(binding.layoutAddWord) {
            when {
                et1Add.text.isNullOrBlank() -> "단어를 입력해주세요."
                et2Add.text.isNullOrBlank() -> "뜻을 입력해주세요."
                else -> null
            }
        }

    private fun isInsertType(): Boolean = intent.getSerializableExtra("word") == null

    override fun onAttachFragment(fragment: Fragment) {
        if (fragment is SearchResultDialog) {
            fragment.setOnWordSelectedListener(this)
        }
    }

    override fun onWordSelected(definition: String) {
        binding.layoutAddWord.et2Add.setText(definition)
    }
}