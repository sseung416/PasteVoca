package co.kr.dgsw.searchvoca.view.activity

import co.kr.dgsw.searchvoca.R
import co.kr.dgsw.searchvoca.base.BaseActivity
import co.kr.dgsw.searchvoca.databinding.ActivityCorrectionsBinding
import co.kr.dgsw.searchvoca.datasource.model.dto.Word
import co.kr.dgsw.searchvoca.view.dialog.WordTestDialog
import co.kr.dgsw.searchvoca.viewmodel.activity.CorrectionsViewModel
import co.kr.dgsw.searchvoca.widget.extension.startActivityWithFinish
import co.kr.dgsw.searchvoca.widget.view.adapter.CorrectionsAdapter
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.android.ext.android.inject

class CorrectionsActivity : BaseActivity<ActivityCorrectionsBinding, CorrectionsViewModel>() {
    override val viewModel by inject<CorrectionsViewModel>()

    override fun init() {
        setupView()
        setupButton()
    }

    private fun setupButton() {
        binding.btnClose.setOnClickListener {
            startActivityWithFinish(MainActivity::class.java)
        }

        binding.btnRetry.setOnClickListener {
            WordTestDialog().show(supportFragmentManager, "")
        }
    }

    private fun setupView() {
        binding.viewpager.adapter = CorrectionsAdapter().apply {
            val list = intent.getSerializableExtra("list") as List<Word>
            viewModel.setList(list)
            setList(viewModel.getList())
        }

        TabLayoutMediator(binding.tabLayout, binding.viewpager) { tab, pos ->
            tab.text = when (pos) {
                0 -> getString(R.string.wrong)
                1 -> getString(R.string.correct)
                2 -> getString(R.string.all)
                else -> "아잉"
            }
        }.attach()
    }
}