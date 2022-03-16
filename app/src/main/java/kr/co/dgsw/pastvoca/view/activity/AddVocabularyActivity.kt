package kr.co.dgsw.pastvoca.view.activity

import kr.co.dgsw.pastvoca.base.BaseActivity
import kr.co.dgsw.pastvoca.databinding.ActivityAddVocabularyBinding
import kr.co.dgsw.pastvoca.viewmodel.activity.AddVocabularyViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddVocabularyActivity : BaseActivity<ActivityAddVocabularyBinding, AddVocabularyViewModel>() {
    override val viewModel by viewModel<AddVocabularyViewModel>()

    override fun init() {
    }

    override fun observeViewModel() {
    }
}