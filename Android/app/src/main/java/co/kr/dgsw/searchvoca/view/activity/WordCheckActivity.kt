package co.kr.dgsw.searchvoca.view.activity

import android.view.animation.AccelerateInterpolator
import co.kr.dgsw.searchvoca.base.BaseActivity
import co.kr.dgsw.searchvoca.databinding.ActivityWordCheckBinding
import co.kr.dgsw.searchvoca.repository.model.dto.VocabularyName
import co.kr.dgsw.searchvoca.viewmodel.activity.WordCheckViewModel
import co.kr.dgsw.searchvoca.widget.livedata.EventObserver
import co.kr.dgsw.searchvoca.widget.view.CardStackAdapter
import co.kr.dgsw.searchvoca.widget.view.adapter.WordCardStackAdapter
import kr.co.dgsw.cardstackview.CardStackLayoutManager
import kr.co.dgsw.cardstackview.Direction
import kr.co.dgsw.cardstackview.Duration
import kr.co.dgsw.cardstackview.SwipeAnimationSetting
import org.koin.androidx.viewmodel.ext.android.viewModel

class WordCheckActivity : BaseActivity<ActivityWordCheckBinding, WordCheckViewModel>(), CardStackAdapter {
    override val viewModel by viewModel<WordCheckViewModel>()
    private var vocabulary: VocabularyName? = null
    private val adapter = WordCardStackAdapter()
    private val manager = CardStackLayoutManager(this)

    override fun init() {
        setupButton()

        vocabulary = intent.getSerializableExtra("vocabulary") as? VocabularyName
        viewModel.getWordsByVocabulary(vocabulary?.id ?: 1)

        binding.tvTitleWordCheck.text = vocabulary?.name

        binding.stackWordCheck.apply {
            adapter = this@WordCheckActivity.adapter
            layoutManager = manager
        }
    }

    override fun observeViewModel() {
        viewModel.words.observe(this, EventObserver {
            adapter.setList(it)
        })
    }

    override fun onCardSwiped(direction: Direction?) {
        // 대충 뭘할까
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

    private fun swipe(direction: Direction) {
        val setting = SwipeAnimationSetting.Builder()
            .setDirection(direction)
            .setDuration(Duration.Normal.duration)
            .setInterpolator(AccelerateInterpolator())
            .build()
        manager.setSwipeAnimationSetting(setting)
        binding.stackWordCheck.swipe()
    }
}