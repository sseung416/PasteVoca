package co.kr.dgsw.searchvoca.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import co.kr.dgsw.searchvoca.BR
import co.kr.dgsw.searchvoca.R
import java.lang.StringBuilder
import java.lang.reflect.ParameterizedType
import java.util.*

abstract class BaseFragment<B : ViewDataBinding, VM : ViewModel> : Fragment() {
    protected abstract val viewModel: VM
    protected lateinit var binding: B

    protected abstract fun init()
    protected abstract fun observeViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        performDataBinding(inflater, container)
        observeViewModel()
        init()
        return binding.root
    }

    private fun performDataBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate<B>(inflater, getLayoutRes(), container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            setVariable(BR.vm, viewModel)
            executePendingBindings()
        }
    }

    @LayoutRes
    private fun getLayoutRes(): Int {
        val split =
            ((Objects.requireNonNull(javaClass.genericSuperclass) as ParameterizedType).actualTypeArguments[0] as Class<*>)
                .simpleName.replace("Binding$".toRegex(), "")
                .split("(?<=.)(?=\\p{Upper})".toRegex())
                .dropLastWhile { it.isEmpty() }.toTypedArray()

        return with (StringBuilder()) {
            for (i in split.indices) {
                this.append(split[i].lowercase(Locale.ROOT))
                if (split.lastIndex != i) this.append("_")
            }

            R.layout::class.java.getField(this.toString()).getInt(R.layout::class.java)
        }
    }
}

