package kr.co.dgsw.pastvoca.view.activity

import android.widget.ArrayAdapter
import kr.co.dgsw.pastvoca.base.BaseActivity
import kr.co.dgsw.pastvoca.databinding.ActivityMainBinding
import kr.co.dgsw.pastvoca.viewmodel.activity.MainViewModel
import kr.co.dgsw.pastvoca.widget.extension.startActivity
import kr.co.dgsw.pastvoca.widget.livedata.EventObserver
import kr.co.dgsw.pastvoca.widget.recyclerview.adapter.WordAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val viewModel by viewModel<MainViewModel>()

    private val wordAdapter = WordAdapter()
    private lateinit var spinnerAdapter: ArrayAdapter<String>

    override fun init() {
        spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arrayOf("전체"))

        binding.rvMain.adapter = wordAdapter
        binding.spinner.adapter = spinnerAdapter

        binding.btnAdd.setOnClickListener {
            this.startActivity(AddWordActivity::class.java)
        }
    }

    override fun observeViewModel() {
        viewModel.apply {
            vocabularyNames.observe(this@MainActivity, EventObserver {
                spinnerAdapter.clear()
                spinnerAdapter.addAll(it)
            })

            words.observe(this@MainActivity, EventObserver {
                wordAdapter.setList(it)
            })
        }
    }
}