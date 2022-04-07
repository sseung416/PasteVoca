package co.kr.dgsw.searchvoca.view.activity

import android.view.View
import android.view.animation.AccelerateInterpolator
import android.widget.Toast
import co.kr.dgsw.searchvoca.base.BaseActivity
import co.kr.dgsw.searchvoca.databinding.ActivityWordCheckBinding
import co.kr.dgsw.searchvoca.datasource.model.dto.VocabularyName
import co.kr.dgsw.searchvoca.datasource.model.dto.Word
import co.kr.dgsw.searchvoca.viewmodel.activity.WordCheckViewModel
import co.kr.dgsw.searchvoca.widget.livedata.EventObserver
import co.kr.dgsw.searchvoca.widget.view.CardStackAdapter
import co.kr.dgsw.searchvoca.widget.view.adapter.WordCardStackAdapter
import kr.co.dgsw.cardstackview.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class WordCheckActivity : BaseActivity<ActivityWordCheckBinding, WordCheckViewModel>(), CardStackAdapter {
    override val viewModel by viewModel<WordCheckViewModel>()

    private var vocabulary: VocabularyName? = null
    private lateinit var words: List<Word>

    private val adapter = WordCardStackAdapter()
    private val manager = CardStackLayoutManager(this, this)

    private var adapterPosition = 0

    override fun init() {
        setupButton()
        setupCardStackView()

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
            words = it
            adapter.setList(it)
        })
    }

    override fun onCardSwiped(direction: Direction?) {
        words[adapterPosition].isCorrect = direction == Direction.Left
    }

    override fun onCardAppeared(view: View?, position: Int) {
        adapterPosition = position
    }

    override fun onCardDisappeared(view: View?, position: Int) {
        if (adapter.itemCount - 1 == position) {
            Toast.makeText(this, "끝!", Toast.LENGTH_SHORT).show()
        }
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