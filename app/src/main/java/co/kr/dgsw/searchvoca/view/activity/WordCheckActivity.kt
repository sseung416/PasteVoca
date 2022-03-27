package co.kr.dgsw.searchvoca.view.activity

import co.kr.dgsw.searchvoca.base.BaseActivity
import co.kr.dgsw.searchvoca.databinding.ActivityWordCheckBinding
import co.kr.dgsw.searchvoca.viewmodel.activity.WordCheckViewModel
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