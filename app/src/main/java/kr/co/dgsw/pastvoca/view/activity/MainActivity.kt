package kr.co.dgsw.pastvoca.view.activity

import kr.co.dgsw.pastvoca.base.BaseActivity
import kr.co.dgsw.pastvoca.databinding.ActivityMainBinding
import kr.co.dgsw.pastvoca.viewmodel.activity.MainViewModel
import kr.co.dgsw.pastvoca.widget.recyclerview.adapter.WordAdapter

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val viewModel: MainViewModel = MainViewModel()

    private val adapter = WordAdapter()

    override fun init() {
        binding.rvMain.adapter = adapter
    }

    override fun observeViewModel() {
    }
}