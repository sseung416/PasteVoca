package co.kr.dgsw.searchvoca.base

import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModel
import co.kr.dgsw.searchvoca.R
import java.lang.StringBuilder
import java.lang.reflect.ParameterizedType
import java.util.*

interface Base<B, VM: ViewModel> {
    val viewModel: VM?
    var binding: B

    fun init()
    fun observeViewModel()

    @LayoutRes
    fun getLayoutRes(): Int {
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