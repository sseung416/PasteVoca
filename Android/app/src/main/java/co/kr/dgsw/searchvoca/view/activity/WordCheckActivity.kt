package co.kr.dgsw.searchvoca.view.activity

import android.content.Intent
import android.view.View
import android.view.animation.AccelerateInterpolator
import co.kr.dgsw.searchvoca.base.BaseActivity
import co.kr.dgsw.searchvoca.databinding.ActivityWordCheckBinding
import co.kr.dgsw.searchvoca.datasource.model.dto.VocabularyName
import co.kr.dgsw.searchvoca.viewmodel.activity.WordCheckViewModel
import co.kr.dgsw.searchvoca.widget.livedata.EventObserver
import co.kr.dgsw.searchvoca.widget.view.CardStackAdapter
import co.kr.dgsw.searchvoca.widget.view.adapter.WordCardStackAdapter
import kr.co.dgsw.cardstackview.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class WordCheckActivity : BaseActivity<ActivityWordCheckBinding, WordCheckViewModel>(), CardStackAdapter {
    override val viewModel by viewModel<WordCheckViewModel>()

    private var vocabulary: VocabularyName? = null

    private val adapter = WordCardStackAdapter()
    private val manager = CardStackLayoutManager(this, this)

    override fun init() {
        vocabulary = intent.getSerializableExtra("vocabulary") as? VocabularyName
        viewModel.getWordsByVocabulary(vocabulary?.id ?: 1)

        setupButton()
        setupCardStackView()

        binding.tvTitleWordCheck.text = vocabulary?.name
    }

    override fun observeViewModel() {
        viewModel.words.observe(this, EventObserver {
            adapter.setList(it)
        })
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

    private fun swipe(direction: Direction) {
        val setting = SwipeAnimationSetting.Builder()
            .setDirection(direction)
            .setDuration(Duration.Normal.duration)
            .setInterpolator(AccelerateInterpolator())
            .build()
        manager.setSwipeAnimationSetting(setting)
        binding.stackWordCheck.swipe()
    }

    private fun setupButton() {
        binding.btnCloseWordCheck.setOnClickListener {
            finish()
        }

        binding.btnCorrectWordCheck.setOnClickListener {
            swipe(Direction.Left)
        }

        binding.btnIncorrectWordCheck.setOnClickListener {
            swipe(Direction.Right)
        }
    }

    private fun setupCardStackView() {
        manager.setSwipeableMethod(SwipeableMethod.AutomaticAndManual)

        binding.stackWordCheck.apply {
            adapter = this@WordCheckActivity.adapter
            layoutManager = manager
        }
    }
}