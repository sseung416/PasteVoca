package co.kr.dgsw.searchvoca.base

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import co.kr.dgsw.searchvoca.BR

abstract class BaseFragment<B : ViewDataBinding, VM : ViewModel> : Fragment(), Base<B, VM> {
    override lateinit var binding: B

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        performDataBinding(inflater, container)
        observeViewModel()
        init()

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onPause() {
        super.onPause()
        requireActivity().overridePendingTransition(0,0)
    }

    override fun observeViewModel() {}

    override fun startActivity(intent: Intent?) {
        intent?.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        super.startActivity(intent)
    }

    private fun performDataBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate<B>(inflater, getLayoutRes(), container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            setVariable(BR.vm, viewModel)
            executePendingBindings()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.unbind()
    }
}

