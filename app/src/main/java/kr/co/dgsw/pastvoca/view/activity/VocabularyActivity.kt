package kr.co.dgsw.pastvoca.view.activity

import kr.co.dgsw.pastvoca.base.BaseActivity
import kr.co.dgsw.pastvoca.databinding.ActivityVocabularyBinding
import kr.co.dgsw.pastvoca.viewmodel.activity.VocabularyViewModel
import kr.co.dgsw.pastvoca.widget.extension.startActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class VocabularyActivity : BaseActivity<ActivityVocabularyBinding, VocabularyViewModel>() {
    override val viewModel by viewModel<VocabularyViewModel>()

    override fun init() {
        binding.btnAddVoca.setOnClickListener {
            startActivity(AddVocabularyActivity::class.java)
        }
    }

    override fun observeViewModel() {
    }
}