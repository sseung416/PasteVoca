package kr.co.dgsw.pastvoca.view.dialog

import android.app.Activity
import android.content.ClipboardManager
import android.os.Bundle
import android.util.Log
import kr.co.dgsw.pastvoca.R

class ClipboardDialog : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clipboard_dialog)
    }
}