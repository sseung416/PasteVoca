package kr.co.dgsw.pastvoca.view.activity

import kr.co.dgsw.pastvoca.base.BaseActivity
import kr.co.dgsw.pastvoca.databinding.ActivityWordCheckBinding
import kr.co.dgsw.pastvoca.viewmodel.activity.WordCheckViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class WordCheckActivity : BaseActivity<ActivityWordCheckBinding, WordCheckViewModel>() {
    override val viewModel by viewModel<WordCheckViewModel>()

    override fun init() {
        TODO("Not yet implemented")
    }

    override fun observeViewModel() {
        TODO("Not yet implemented")
    }

}