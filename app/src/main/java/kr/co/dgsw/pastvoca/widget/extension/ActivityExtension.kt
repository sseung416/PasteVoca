package kr.co.dgsw.pastvoca.widget.extension

import android.app.Activity
import android.content.Intent

fun Activity.startActivity(toActivity: Class<*>) {
    startActivity(Intent(this, toActivity).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION))
}

fun Activity.startActivityWithFinish(toActivity: Class<*>) {
    startActivity(Intent(this, toActivity).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
    finish()
}