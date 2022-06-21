package co.kr.dgsw.searchvoca.widget.extension

import android.app.Activity
import android.content.Intent

fun Activity.startActivity(toActivity: Class<*>) {
    startActivity(Intent(this, toActivity).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION))
}

fun Activity.startActivityWithFinish(toActivity: Class<*>) {
    val intent = Intent(this, toActivity)
        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP and Intent.FLAG_ACTIVITY_NO_ANIMATION)
    startActivity(intent)
    finish()
}

