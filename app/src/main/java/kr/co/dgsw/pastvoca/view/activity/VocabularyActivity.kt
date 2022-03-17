package kr.co.dgsw.pastvoca.view.activity

import kr.co.dgsw.pastvoca.R
import kr.co.dgsw.pastvoca.base.BaseActivity
import kr.co.dgsw.pastvoca.databinding.ActivityVocabularyBinding
import kr.co.dgsw.pastvoca.viewmodel.activity.VocabularyViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class VocabularyActivity : BaseActivity<ActivityVocabularyBinding, VocabularyViewModel>() {
    override val viewModel by viewModel<VocabularyViewModel>()

    override fun init() {
        binding.btnAddVoca.setOnClickListener {

        }
    }

    override fun observeViewModel() {
    }
}