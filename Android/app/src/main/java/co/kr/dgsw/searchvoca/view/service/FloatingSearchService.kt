package co.kr.dgsw.searchvoca.view.service

import android.content.Intent
import android.view.Gravity
import android.view.WindowManager
import android.view.WindowManager.LayoutParams.*
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageButton
import co.kr.dgsw.searchvoca.R

class FloatingSearchService : FloatingService() {
    private lateinit var etSearch: EditText
    private lateinit var btnClose: ImageButton

    override val windowLayoutParams: WindowManager.LayoutParams =
        getWindowParams(MATCH_PARENT, WRAP_CONTENT, FLAG_NOT_FOCUSABLE, Gravity.BOTTOM)

    override fun getLayoutRes(): Int = R.layout.layout_floating_search

    override fun init() {
        viewInit()

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
                        val intent = Intent(this@FloatingSearchService, FloatingSearchResultService::class.java)
                            .putExtra("word", text.toString())
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

    private fun viewInit() {
        etSearch = viewGroup.findViewById(R.id.et_word_floating_search)
        btnClose = viewGroup.findViewById(R.id.btn_close_floating_search)
    }
}