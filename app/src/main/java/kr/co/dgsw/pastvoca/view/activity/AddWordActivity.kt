package kr.co.dgsw.pastvoca.view.activity

import kr.co.dgsw.pastvoca.base.BaseActivity
import kr.co.dgsw.pastvoca.databinding.ActivityAddWordBinding
import kr.co.dgsw.pastvoca.viewmodel.activity.AddWordViewModel
import kr.co.dgsw.pastvoca.widget.extension.startActivityWithFinish
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddWordActivity : BaseActivity<ActivityAddWordBinding, AddWordViewModel>() {
    override val viewModel by viewModel<AddWordViewModel>()

    override fun init() {
        binding.layoutAddWord.apply {
            btnBack.setOnClickListener {
                this@AddWordActivity.startActivityWithFinish(MainActivity::class.java)
            }

            btnConfirm.setOnClickListener {
                viewModel.insertWord()
            }
        }
    }

    override fun observeViewModel() {
    }
}