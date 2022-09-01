package co.kr.searchvoca.ui.setting.service

import android.content.Intent
import android.view.Gravity
import android.view.WindowManager
import android.view.WindowManager.LayoutParams.*
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageButton
import co.kr.searchvoca.R

class FloatingSearchService : FloatingService(R.layout.layout_floating_search) {

    private val etSearch: EditText by lazy {
        viewGroup.findViewById(R.id.et_word_floating_search)
    }

    private val btnClose: ImageButton by lazy {
        viewGroup.findViewById(R.id.btn_close_floating_search)
    }

    override val windowLayoutParams: WindowManager.LayoutParams =
        getWindowParams(MATCH_PARENT, WRAP_CONTENT, FLAG_NOT_FOCUSABLE, Gravity.BOTTOM)

    override fun init() {
        etSearch.apply {
            setOnTouchListener { _, _ ->
                isCursorVisible = true
                windowLayoutParams.flags = FLAG_NOT_TOUCH_MODAL and FLAG_LAYOUT_IN_SCREEN
                updateViewLayout()
                return@setOnTouchListener false
            }

            setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (text.trim().isNotBlank()) {
                        val intent = Intent(
                            this@FloatingSearchService,
                            FloatingSearchResultService::class.java
                        ).putExtra("word", text.toString())
                        startService(intent)
                        stopService()
                    }
                }
                return@setOnEditorActionListener true
            }
        }

        btnClose.setOnClickListener {
            stopService()
        }
    }
}