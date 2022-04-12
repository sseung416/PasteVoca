package co.kr.dgsw.searchvoca.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import co.kr.dgsw.searchvoca.BR

abstract class BaseActivity<B: ViewDataBinding, VM: ViewModel> : AppCompatActivity(), Base<B, VM> {
    override lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
        init()
        observeViewModel()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.unbind()
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(0, 0)
    }

    override fun startActivity(intent: Intent?) {
        intent?.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        super.startActivity(intent)
    }

    override fun observeViewModel() {}

    private fun performDataBinding() {
        binding = DataBindingUtil.setContentView<B>(this, getLayoutRes()).apply {
            lifecycleOwner = this@BaseActivity
            setVariable(BR.vm, viewModel)
            executePendingBindings()
        }
    }
}