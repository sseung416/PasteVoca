package co.kr.searchvoca.shared.android.component

import android.content.Context
import android.graphics.PorterDuff
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.util.AttributeSet
import android.util.Log
import androidx.annotation.ColorRes
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.content.ContextCompat
import co.kr.searchvoca.shared.android.R
import co.kr.searchvoca.shared.domain.Translate

class TextToSpeechButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyle: Int = 0
) : AppCompatImageButton(context, attrs, defStyle) {

    var tts: TextToSpeech? = null

    var text: String? = null

    var language: Translate? = null
        set(value) {
            field = value
            tts?.language = value!!.locale
        }

    init {
        setOnClickListener {
            speak()
        }
        setOnSpeechListener(null)

        setImageResource(R.drawable.ic_sound)
        setupMinimumSize()
        setPadding()
        setTransparentBackground()
    }

    fun speak() {
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    fun destroy() {
        tts?.stop()
        tts?.shutdown()
    }

    fun setOnSpeechListener(listener: OnSpeechListener? = null) {
        // todo 쓰레드 변경
        tts?.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
            override fun onStart(p0: String?) {
                isEnabled = false
                setSoundButtonColor(R.color.sound_highlight)
                listener?.onStart()
            }

            override fun onDone(p0: String?) {
                isEnabled = true
                setSoundButtonColor(R.color.sound_none)
                listener?.onDone()
            }

            @Deprecated("Deprecated in Java")
            override fun onError(p0: String?) {}
        })
    }

    private fun setupMinimumSize() {
        val minSize = resources.getDimensionPixelSize(R.dimen.size_click_minimum)
        this.minimumHeight = minSize
        this.minimumWidth = minSize
    }

    private fun setPadding() {
        val padding = context.resources.getDimensionPixelSize(R.dimen.margin_normal)
        setPadding(padding, padding, padding, padding)
    }

    private fun setTransparentBackground() {
        val color = ContextCompat.getColor(context, android.R.color.transparent)
        setBackgroundColor(color)
    }

    private fun setSoundButtonColor(@ColorRes colorRes: Int) {
        setColorFilter(ContextCompat.getColor(context, colorRes), PorterDuff.Mode.SRC_ATOP)
    }
}

class OnIniListenerImpl(
    private val onSuccess: (() -> Unit)? = null
) : TextToSpeech.OnInitListener {

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            onSuccess?.invoke()
            Log.e("TextToSpeech", "Initialization Success!")
        } else {
            Log.e("TextToSpeech", "Initialization Failed!")
        }
    }
}

interface OnSpeechListener {
    fun onStart()
    fun onDone()
}