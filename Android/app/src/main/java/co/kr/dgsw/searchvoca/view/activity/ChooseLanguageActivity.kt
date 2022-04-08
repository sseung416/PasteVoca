package co.kr.dgsw.searchvoca.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.kr.dgsw.searchvoca.base.BaseActivity
import co.kr.dgsw.searchvoca.databinding.ActivityChooseLanguageBinding
import co.kr.dgsw.searchvoca.viewmodel.activity.ChooseLanguageViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChooseLanguageActivity : BaseActivity<ActivityChooseLanguageBinding, ChooseLanguageViewModel>() {
    override val viewModel by viewModel<ChooseLanguageViewModel>()

    override fun init() {
    }

    override fun observeViewModel() {
    }
}