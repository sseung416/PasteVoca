package kr.co.dgsw.pastvoca.service

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Build
import android.os.IBinder
import android.view.*
import android.widget.ImageButton
import kr.co.dgsw.pastvoca.R
import kr.co.dgsw.pastvoca.view.dialog.ClipboardDialog

class FloatingService : Service() {
    private lateinit var windowManager: WindowManager
    private lateinit var floatView: ViewGroup

    override fun onCreate() {
        super.onCreate()

        windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
        (getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater).apply {
            floatView = inflate(R.layout.layout_floating, null) as ViewGroup
        }

        val floatWindowLayoutParams = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            getLayoutType(),
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        ).apply {
            gravity = Gravity.END
            x = 0
            y = 0
        }

        floatView.findViewById<ImageButton>(R.id.btn_search_floating).apply {
            setOnClickListener {
                val intent = Intent(this@FloatingService, ClipboardDialog::class.java)
                PendingIntent.getActivity(
                    this@FloatingService,
                    0,
                    intent,
                    PendingIntent.FLAG_ONE_SHOT
                ).send()
            }

            setOnTouchListener(object : View.OnTouchListener {
                private var x = 0f
                private var y = 0f
                private var px = 0f
                private var py = 0f

                override fun onTouch(view: View?, event: MotionEvent?): Boolean {
                    when (event?.action) {
                        MotionEvent.ACTION_DOWN -> {
                            x = floatWindowLayoutParams.x.toFloat()
                            y = floatWindowLayoutParams.y.toFloat()
                            px = event.rawX
                            py = event.rawY
                        }

                        MotionEvent.ACTION_MOVE -> {
                            floatWindowLayoutParams.x = ((x + event.rawX) - px).toInt()
                            floatWindowLayoutParams.y = ((y + event.rawY) - py).toInt()

                            windowManager.updateViewLayout(floatView, floatWindowLayoutParams)
                        }
                    }

                    return false
                }
            })
        }

        windowManager.addView(floatView, floatWindowLayoutParams)
    }

    override fun onBind(intent: Intent): IBinder? = null

    override fun onDestroy() {
        super.onDestroy()
        stopSelf()
        windowManager.removeView(floatView)
    }

    private fun getLayoutType() =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
        else WindowManager.LayoutParams.TYPE_TOAST
}