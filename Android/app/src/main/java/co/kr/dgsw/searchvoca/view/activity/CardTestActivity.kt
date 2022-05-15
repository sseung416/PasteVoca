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
import co.kr.dgsw.searchvoca.widget.extension.startActivity
import co.kr.dgsw.searchvoca.R
import co.kr.dgsw.searchvoca.datasource.model.dto.CorrectionsWord
import co.kr.dgsw.searchvoca.datasource.model.dto.Vocabulary
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

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
            val list = it.shuffled().slice(0 until count).map { data ->
                CorrectionsWord(
                    data.word,
                    data.meaning,
                    0
                )
            }
            adapter.setList(list)
        })

        viewModel.correctionsVocabularyId.observe(this, EventObserver { id ->
            val list = adapter.getList()
            list.forEach { it.vocabularyId = id }
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
            insertCorrectionsVocabulary()

            adapter.getList().forEach {
                viewModel.insertCorrectionsWord(it)
            }
            startActivity(CorrectionsActivity::class.java)
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

    private fun insertCorrectionsVocabulary() {
        viewModel.insertVocabulary(
            Vocabulary(vocabulary?.name ?: "", isCorrections = true, date = Date().time)
        )
        viewModel.getLastCorrectionsVocabularyId()
    }
}