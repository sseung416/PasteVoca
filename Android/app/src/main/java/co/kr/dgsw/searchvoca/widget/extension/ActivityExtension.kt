package co.kr.dgsw.searchvoca.widget.extension

import android.app.Activity
import android.app.ActivityManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

fun Activity.startActivity(toActivity: Class<*>) {
    startActivity(Intent(this, toActivity).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION))
}

fun Activity.startActivityWithFinish(toActivity: Class<*>) {
    startActivity(Intent(this, toActivity).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
    finish()
}

@Suppress("DEPRECATED")
fun Activity.isFloatingServiceRunning(): Boolean =
    (getSystemService(AppCompatActivity.ACTIVITY_SERVICE) as ActivityManager)
        .getRunningServices(Int.MAX_VALUE)
        .any { it.service.className == this::class.java.name }


