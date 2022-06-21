package co.kr.dgsw.searchvoca.view.fragment

import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import co.kr.dgsw.searchvoca.base.BaseFragment
import co.kr.dgsw.searchvoca.databinding.FragmentListeningTestBinding
import co.kr.dgsw.searchvoca.view.data.TestWord
import co.kr.dgsw.searchvoca.viewmodel.fragment.ListeningTestViewModel
import co.kr.dgsw.searchvoca.widget.extension.removeSpecialSymbol
import co.kr.dgsw.searchvoca.widget.extension.showQuitTestDialog
import co.kr.dgsw.searchvoca.widget.livedata.EventObserver
import org.koin.android.ext.android.inject
import java.util.*

class ListeningTestFragment : BaseFragment<FragmentListeningTestBinding, ListeningTestViewModel>(),
    TextToSpeech.OnInitListener {
    override val viewModel by inject<ListeningTestViewModel>()

    private val navController by lazy { findNavController() }

    private lateinit var tts: TextToSpeech
    private val wordList by lazy {
        requireActivity().intent.getParcelableArrayListExtra<TestWord>("list") as List<TestWord>
    }

    private val wordListLastIndex by lazy { wordList.lastIndex }

    override fun init() {
        hasOptionsMenu()
        binding.etAnswer.requestFocus()

        viewModel.testWord = wordList[0]
        tts = TextToSpeech(requireContext(), this)
    }

    override fun onDestroy() {
        tts.stop()
        tts.shutdown()
        super.onDestroy()
    }

    override fun observeViewModel() {
        viewModel.apply {
            currentWord.observe(viewLifecycleOwner, EventObserver { (isCorrect, idx) ->
                Log.e("observeViewModel", "currentWord: correct=$isCorrect, index=$idx")
                wordList[idx - 1].isCorrect = isCorrect

                if (idx > wordListLastIndex) {
                    navigateToCorrections()
                    return@EventObserver
                }

                val testWord = wordList[idx]
                this.testWord = testWord
                userAnswer.value = ""
                speakOut(testWord.word.removeSpecialSymbol())
            })

            soundClickEvent.observe(viewLifecycleOwner) {
                speakOut(testWord!!.word.removeSpecialSymbol())
            }

            wrongAnswerEvent.observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(), "오답이에요. 다시 입력해주세요.", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> showQuitTestDialog()
        }
        return true
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts.apply {
                language = Locale.US
                setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                    override fun onStart(p0: String?) {}

                    override fun onDone(p0: String?) {
                        viewModel.soundEnabled.postValue(true)
                    }

                    override fun onError(p0: String?) {}
                })
            }

            speakOut(viewModel.testWord!!.word)
        } else {
            Log.e("TextToSpeech", "Initialization Failed!")
        }
    }

    private fun speakOut(text: String) {
        viewModel.soundEnabled.value = false
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, "id1")
    }

    private fun navigateToCorrections() {
        navController.navigate(
            ListeningTestFragmentDirections.actionToCorrectionsFragment(
                wordList.toTypedArray()
            )
        )
    }
}