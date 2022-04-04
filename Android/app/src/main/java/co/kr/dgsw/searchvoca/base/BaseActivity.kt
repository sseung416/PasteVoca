package co.kr.dgsw.searchvoca.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import co.kr.dgsw.searchvoca.BR
import co.kr.dgsw.searchvoca.R
import java.lang.StringBuilder
import java.lang.reflect.ParameterizedType
import java.util.*

abstract class BaseActivity<B: ViewDataBinding, VM: ViewModel> : AppCompatActivity() {
    protected lateinit var binding: B
    abstract val viewModel: VM

    protected abstract fun init()
    protected abstract fun observeViewModel()

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

    private fun performDataBinding() {
        binding = DataBindingUtil.setContentView(this, getLayoutRes())
        binding.setVariable(BR.vm, viewModel)
        binding.executePendingBindings()
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(0, 0)
    }

    @LayoutRes
    private fun getLayoutRes(): Int {
        val split = ((Objects.requireNonNull(javaClass.genericSuperclass) as ParameterizedType).actualTypeArguments[0] as Class<*>)
            .simpleName.replace("Binding$".toRegex(), "")
            .split("(?<=.)(?=\\p{Upper})".toRegex())
            .dropLastWhile { it.isEmpty() }.toTypedArray()

        return with (StringBuilder()) {
            for (i in split.indices) {
                append(split[i].lowercase(Locale.ROOT))
                if (i != split.lastIndex) append("_")
            }

            R.layout::class.java.getField(this.toString()).getInt(R.layout::class.java)
        }
    }
}