package co.kr.dgsw.searchvoca.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import co.kr.dgsw.searchvoca.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheetDialog<T: ViewDataBinding>(
    @LayoutRes private val layoutRes: Int
) : BottomSheetDialogFragment() {

    private var _binding: T? = null
    protected val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        val size = getDialogSize()
        if (size != null)
            dialog?.window?.setLayout(size.first, size.second)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.unbind()
    }

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    open fun getDialogSize(): Pair<Int, Int>? = null
}