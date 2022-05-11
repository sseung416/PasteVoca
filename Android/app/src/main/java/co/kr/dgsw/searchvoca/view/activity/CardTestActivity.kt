package co.kr.dgsw.searchvoca.view.activity

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import co.kr.dgsw.searchvoca.base.BaseActivity
import co.kr.dgsw.searchvoca.databinding.ActivityCardTestBinding
import co.kr.dgsw.searchvoca.datasource.model.dto.VocabularyName
import co.kr.dgsw.searchvoca.viewmodel.activity.CardTestViewModel
import co.kr.dgsw.searchvoca.widget.livedata.EventObserver
import co.kr.dgsw.searchvoca.widget.view.CardStackAdapter
import co.kr.dgsw.searchvoca.widget.view.adapter.WordCardStackAdapter
import kr.co.dgsw.cardstackview.*
import co.kr.dgsw.searchvoca.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class CardTestActivity : BaseActivity<ActivityCardTestBinding, CardTestViewModel>(), CardStackAdapter {
    override val viewModel by viewModel<CardTestViewModel>()

    private val vocabulary by lazy {
        intent?.getSerializableExtra("vocabulary") as? VocabularyName
    }
    private val problemCount by lazy {
        intent?.getIntExtra("problemCount", 0)
    }

    private val adapter = WordCardStackAdapter()
    private val manager = CardStackLayoutManager(this, this)

    override fun init() {
        if (vocabulary?.id == null) viewModel.getAllWords()
        else viewModel.getWordsByVocabulary(vocabulary?.id!!)

        binding.toolbarTitleCardTest.text = vocabulary?.name

        setupToolbar()
        setupCardStackView()
    }

    override fun observeViewModel() {
        viewModel.words.observe(this, EventObserver {
            val count = if (problemCount == null || problemCount == 0) it.size else problemCount!!
            val list = it.shuffled().slice(0 until count)
            adapter.setList(list)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_default, menu)
        return true
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                // todo 재차 확인
                finish()
                true
            }
            else -> false
        }
    }

    override fun onCardSwiped(direction: Direction?) {
        // 왼쪽으로 스와이프되면 정답을 체크했으므로, isCorrect 를 true 로 설정
        adapter.setCorrect(direction == Direction.Left)
    }

    override fun onCardDisappeared(view: View?, position: Int) {
        if (adapter.itemCount - 1 == position) {
            val intent = Intent(this, CorrectionsActivity::class.java)
                .putExtra("list", adapter.getList())
            startActivity(intent)
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbarCardTest)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setupCardStackView() {
        manager.setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
        binding.stackWordCheck.apply {
            adapter = this@CardTestActivity.adapter
            layoutManager = manager
        }
    }
}