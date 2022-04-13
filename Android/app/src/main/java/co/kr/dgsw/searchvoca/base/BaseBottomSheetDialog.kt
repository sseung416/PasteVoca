package co.kr.dgsw.searchvoca.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import co.kr.dgsw.searchvoca.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheetDialog<B: ViewBinding> : BottomSheetDialogFragment(), Base<B, ViewModel> {
    override val viewModel: ViewModel? = null
    override lateinit var binding: B

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getViewBinding(inflater, container)
        init()
        observeViewModel()
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        val size = getDialogSize()
        if (size != null)
            dialog?.window?.setLayout(size.first, size.second)
    }

    override fun observeViewModel() {}

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    protected abstract fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): B

    open fun getDialogSize(): Pair<Int, Int>? = null
}