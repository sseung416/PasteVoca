package kr.co.dgsw.pastvoca.view.activity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
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

    private var clicked = false

    override fun init() {
        viewModel.getVocabularyNames()
        viewModel.getAllWords()

        spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arrayListOf("전체"))

        wordAdapter.setList(listOf())

        binding.rvMain.adapter = wordAdapter
        binding.spinner.adapter = spinnerAdapter

        binding.btnAdd.setOnClickListener {
            startActivity(AddWordActivity::class.java)
        }

        binding.btnMenuMain.setOnClickListener {
            if (clicked) {
                showOutFab(binding.btnSortMain, binding.btnVocaMain, binding.btnWordCheckMain)
                clicked = false
            } else {
                showInFab(binding.btnWordCheckMain, binding.btnVocaMain, binding.btnSortMain)
                clicked = true
            }
        }
        
        binding.btnWordCheckMain.setOnClickListener {
        }

        binding.btnSortMain.setOnClickListener {

        }

        binding.btnVocaMain.setOnClickListener {
            startActivity(VocabularyActivity::class.java)
        }
    }

    override fun observeViewModel() {
        viewModel.apply {
            vocabularyNames.observe(this@MainActivity, EventObserver {
                spinnerAdapter.clear()
                spinnerAdapter.notifyDataSetChanged()
                spinnerAdapter.addAll(it)
                spinnerAdapter.notifyDataSetChanged()
            })

            words.observe(this@MainActivity, EventObserver {
                wordAdapter.setList(it)
            })
        }
    }

    private fun showInFab(vararg views: View) {
        var delay = 0L

        views.forEach {
            it.apply {
                visibility = VISIBLE
                alpha = 0f
                translationY = height.toFloat()
                animate().setDuration(200)
                    .translationY(0f)
                    .alpha(1f)
                    .setStartDelay(delay)
                    .start()

            }

            delay += 100L
        }
    }

    private fun showOutFab(vararg views: View) {
        var delay = 0L

        views.forEach {
            it.apply {
                alpha = 1f
                translationY = 0f
                animate().setDuration(200)
                    .translationY(height.toFloat())
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator?) {
                            visibility = GONE
                            super.onAnimationEnd(animation)
                        }
                    }).alpha(0f)
                    .setStartDelay(delay)
                    .start()
            }

            delay += 100L
        }
    }
}