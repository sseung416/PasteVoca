package kr.co.dgsw.pastvoca.service

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kr.co.dgsw.pastvoca.view.dialog.ClipboardDialog

class ClipboardService : Service() {

    override fun onCreate() {
        super.onCreate()
        showDialog()
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    private fun showDialog() {
        val intent = Intent(this, ClipboardDialog::class.java)
        PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT).send()
    }

    companion object {
        private const val TAG = "ClipboardService"
    }
}