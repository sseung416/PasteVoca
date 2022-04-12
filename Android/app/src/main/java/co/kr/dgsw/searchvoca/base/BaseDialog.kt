package co.kr.dgsw.searchvoca.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseDialog<B : ViewBinding> : DialogFragment(), Base<B, ViewModel> {
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

    protected abstract fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): B

    open fun getDialogSize(): Pair<Int, Int>? = null
}