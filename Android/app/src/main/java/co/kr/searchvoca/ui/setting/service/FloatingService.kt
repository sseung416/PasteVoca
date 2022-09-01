package co.kr.searchvoca.ui.setting.service

import android.content.Intent
import android.graphics.PixelFormat
import android.os.Build
import android.os.IBinder
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.lifecycle.LifecycleService

abstract class FloatingService(@LayoutRes layoutRes: Int) : LifecycleService() {

    private val windowManager: WindowManager by lazy {
        getSystemService(WINDOW_SERVICE) as WindowManager
    }

    protected val viewGroup: ViewGroup by lazy {
        val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(layoutRes, null) as ViewGroup
    }

    protected abstract val windowLayoutParams: WindowManager.LayoutParams

    protected abstract fun init()

    override fun onCreate() {
        super.onCreate()
        init()
        windowManager.addView(viewGroup, windowLayoutParams)
    }

    override fun onDestroy() {
        super.onDestroy()
        stopService()
    }

    protected fun updateViewLayout() {
        windowManager.updateViewLayout(viewGroup, windowLayoutParams)
    }

    protected fun getWindowParams(
        width: Int,
        height: Int,
        flags: Int,
        gravity: Int
    ): WindowManager.LayoutParams =
        WindowManager.LayoutParams(
            width,
            height,
            getLayoutType(),
            flags,
            PixelFormat.TRANSLUCENT
        ).apply {
            this.gravity = gravity
            x = 0
            y = 0
        }

    protected fun stopService() {
        stopSelf()
        (getSystemService(WINDOW_SERVICE) as WindowManager).removeView(viewGroup)
    }

    private fun getLayoutType() =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
        else WindowManager.LayoutParams.TYPE_TOAST
}