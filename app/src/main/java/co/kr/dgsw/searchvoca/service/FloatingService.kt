package co.kr.dgsw.searchvoca.service

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Build
import android.os.IBinder
import android.view.*
import android.view.View.*
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import co.kr.dgsw.searchvoca.view.dialog.ClipboardDialog
import co.kr.dgsw.searchvoca.R

class FloatingService : Service() {
    private lateinit var windowManager: WindowManager
    private lateinit var floatView: ViewGroup

    private var isFirstTouched = false

    private lateinit var imageView: ImageView
    private lateinit var etSearch: EditText
    private lateinit var btnSearch: ImageButton

    override fun onCreate() {
        super.onCreate()

        init()

        val floatWindowLayoutParams = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            getLayoutType(),
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        ).apply {
            gravity = Gravity.CENTER
            x = 0
            y = 0
        }


        btnSearch.apply {
            setOnClickListener {
                val location = IntArray(2)
                floatView.getLocationOnScreen(location)
                val x = location[0] + floatView.width / 2
                val screenWidth = getScreenWidth()

                if (etSearch.visibility == VISIBLE) {
                    val intent = Intent(this@FloatingService, ClipboardDialog::class.java)
                        .putExtra("keyword", etSearch.text)
                    PendingIntent.getActivity(this@FloatingService, 0, intent, PendingIntent.FLAG_ONE_SHOT)
                    // todo 사전 검색
                    etSearch.visibility = GONE
                } else {
                    val (startSide, endSide) =
                        if (x < screenWidth / 2) Pair(ConstraintSet.START, ConstraintSet.END)
                        else Pair(ConstraintSet.END, ConstraintSet.START)

                    val constraint = floatView.findViewById<ConstraintLayout>(R.id.constraint_floating)
                    ConstraintSet().apply {
                        clone(constraint)
                        connect(etSearch.id, startSide, btnSearch.id, endSide)
                        connect(etSearch.id, endSide, constraint.id, endSide)

                        clear(btnSearch.id, endSide)
                        connect(btnSearch.id, startSide, constraint.id, startSide)
                        applyTo(constraint)
                    }

                    etSearch.visibility = VISIBLE
                }
            }

            setOnTouchListener(object : OnTouchListener {
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
//
//                    if (!isFirstTouched) {
//                        imageView.visibility = GONE
//                        isFirstTouched = true
//                    }

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

    private fun init() {
        windowManager = getSystemService(WINDOW_SERVICE) as WindowManager

        (getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater).apply {
            floatView = inflate(R.layout.layout_floating, null) as ViewGroup
        }

        imageView = floatView.findViewById(R.id.iv_here_floating)
        btnSearch = floatView.findViewById(R.id.btn_search_floating)
        etSearch = floatView.findViewById(R.id.et_floating)
    }

    private fun getLayoutType() =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
        else WindowManager.LayoutParams.TYPE_TOAST

    private fun getScreenWidth() = application.resources.displayMetrics.widthPixels

    private fun getScreenSize() = with(applicationContext.resources.displayMetrics) {
        Pair(this.widthPixels, this.heightPixels)
    }

    private fun animationUpDown(view: View) {
        view.apply {
            animate().setDuration(500)
                .translationY(-20f)
                .translationY(20f)
                .start()
        }
    }
}