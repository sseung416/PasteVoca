package co.kr.dgsw.searchvoca.view.fragment

import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.get
import co.kr.dgsw.searchvoca.R
import co.kr.dgsw.searchvoca.base.BaseFragment
import co.kr.dgsw.searchvoca.databinding.FragmentHomeBinding
import co.kr.dgsw.searchvoca.view.activity.AddWordActivity
import co.kr.dgsw.searchvoca.view.activity.SearchWordActivity
import co.kr.dgsw.searchvoca.view.dialog.DefaultBottomSheetDialog
import co.kr.dgsw.searchvoca.view.dialog.WordBottomSheetDialog
import co.kr.dgsw.searchvoca.viewmodel.dialog.WordBottomSheetViewModel
import co.kr.dgsw.searchvoca.viewmodel.fragment.HomeViewModel
import co.kr.dgsw.searchvoca.widget.extension.setOnClickListenerThrottled
import co.kr.dgsw.searchvoca.widget.extension.startActivity
import co.kr.dgsw.searchvoca.widget.livedata.EventObserver
import co.kr.dgsw.searchvoca.widget.view.adapter.WordAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val viewModel by viewModel<HomeViewModel>()
    private val wordDialogViewModel by sharedViewModel<WordBottomSheetViewModel>()

    private val wordAdapter = WordAdapter()
    private lateinit var tts: TextToSpeech

    private var currentPos: Int = 0

    override fun init() {
        viewModel.getVocabularyNames()
        viewModel.getAllWords()
        viewModel.getSearchWords()

        setupToolbar()
        setupRecyclerView()
        setupButton()
        setupTTS()
    }

    override fun observeViewModel() {
        viewModel.apply {
            allWords.observe(viewLifecycleOwner, EventObserver {
                wordAdapter.setList(it)
            })

            wordsByVoca.observe(viewLifecycleOwner, EventObserver {
                if (it.isEmpty())
                    Toast.makeText(requireContext(), "단어가 없어요! 단어를 추가해주세요.", Toast.LENGTH_LONG).show()
                else wordAdapter.setList(it)
            })

            ttsWord.observe(viewLifecycleOwner, EventObserver { (langCode, word) ->
                tts.language = langCode
                speak(word)
            })
        }

        wordDialogViewModel.deleteEvent.observe(viewLifecycleOwner) {
            viewModel.getAllWords()
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.apply {
            getAllWords()
            getSearchWords()
            getVocabularyNames()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.toolbar_home, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.toolbar_filter_home -> {
                val list = listOf(
                    Pair(WordAdapter.SHUFFLE, "랜덤"),
                    Pair(WordAdapter.SORT_DIFFICULT, "어려운순으로"),
                    Pair(WordAdapter.SORT_EASY, "쉬운순으로"),
                )

                DefaultBottomSheetDialog(list, { data ->
                    // todo 단어 순서 저장
                    wordAdapter.sort(data.first!!)
                }).show(parentFragmentManager, "")
                true
            }

            R.id.toolbar_add_home -> {
                requireActivity().startActivity(AddWordActivity::class.java)
                true
            }
            else -> false
        }

    override fun onDestroy() {
        if (this::tts.isInitialized) {
            tts.stop()
            tts.shutdown()
        }
        super.onDestroy()
    }

    private fun setupRecyclerView() {
        binding.rvHome.adapter = wordAdapter.apply {
            onLongClickWordListener = listener@{
                WordBottomSheetDialog(it).show(parentFragmentManager, WordBottomSheetDialog.TAG)
                return@listener true
            }

            onClickTypeListener = {
                viewModel.updateWord(it)
            }

            onClickSoundListener = { word, pos ->
                viewModel.detectWord(resources, word)
                currentPos = pos

                findWordViewHolderForAdapterPosition(pos).apply {
                    setSoundButtonEnabled(false)
                    setSoundButtonHighlight(requireContext(), R.color.sound_highlight)
                }
            }
        }
    }

    private fun setupButton() {
        binding.cvWord.setOnClickListener {
            requireActivity().startActivity(SearchWordActivity::class.java)
        }
    }

    private fun setupToolbar() {
        requireActivity().findViewById<TextView>(R.id.toolbar_title_main).visibility = GONE

        requireActivity().findViewById<LinearLayout>(R.id.toolbar_spinner_main).apply {
            visibility = VISIBLE
            val spinnerName = this[0] as TextView

            setOnClickListenerThrottled {
                val list = arrayListOf(Pair<Int?, String>(null, "전체")).apply {
                    val vocabularyList = viewModel.vocabularyNames.value?.peekContent()
                        ?.map { Pair(it.id, it.name!!) } ?: listOf()
                    addAll(vocabularyList)
                }

                DefaultBottomSheetDialog(list, {
                    when {
                        it.second == spinnerName.text -> {}
                        it.first == null -> viewModel.getAllWords()
                        else -> viewModel.getWordsByVocabulary(it.first!!)
                    }

                    spinnerName.text = it.second
                }).show(parentFragmentManager, "")
            }
        }
    }

    private fun setupTTS() {
        tts = TextToSpeech(requireContext()) { state ->
            if (state == TextToSpeech.SUCCESS) {
                val result = tts.setLanguage(Locale.US)

                if (result == TextToSpeech.LANG_NOT_SUPPORTED || result == TextToSpeech.LANG_MISSING_DATA) {
                    Toast.makeText(requireContext(), "아직 지원되지 않은 언어입니다.", Toast.LENGTH_LONG).show()
                } else {
                    tts.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                        override fun onStart(p0: String?) {}

                        override fun onDone(p0: String?) {
                            CoroutineScope(Dispatchers.Main).launch {
                                findWordViewHolderForAdapterPosition(currentPos).apply {
                                    setSoundButtonEnabled(true)
                                    setSoundButtonHighlight(requireContext(), R.color.sound_none)
                                }
                            }
                        }

                        override fun onError(p0: String?) {}
                    })
                }
            }
        }
    }

    private fun speak(text: String?) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, "id1")
    }

    private fun findWordViewHolderForAdapterPosition(position: Int) =
        binding.rvHome.findViewHolderForAdapterPosition(position) as WordAdapter.ViewHolder
}