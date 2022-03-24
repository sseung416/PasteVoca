package kr.co.dgsw.pastvoca.view.activity

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import kr.co.dgsw.pastvoca.widget.extension.startActivityWithFinish

class PermissionActivity : AppCompatActivity() {
    private val result =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (!Settings.canDrawOverlays(this)) {
                // todo permission 동의 받지 못했을 때 처리해주기..
            } else {
                startMainActivity()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkPermission()
    }

    private fun checkPermission() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:$packageName")
                ).apply { result.launch(this) }
            } else {
                startMainActivity()
            }
        } else {
            startMainActivity()
        }
    }

    private fun startMainActivity() {
        startActivityWithFinish(MainActivity::class.java)
    }
}