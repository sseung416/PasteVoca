package co.kr.dgsw.searchvoca.view.fragment

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.view.View
import android.view.View.VISIBLE
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import co.kr.dgsw.searchvoca.R
import co.kr.dgsw.searchvoca.base.BaseFragment
import co.kr.dgsw.searchvoca.databinding.FragmentSettingBinding
import co.kr.dgsw.searchvoca.view.service.FloatingSearchButtonService
import co.kr.dgsw.searchvoca.viewmodel.fragment.SettingViewModel
import co.kr.dgsw.searchvoca.widget.extension.startService
import co.kr.dgsw.searchvoca.widget.extension.stopService
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingFragment : BaseFragment<FragmentSettingBinding, SettingViewModel>() {
    override val viewModel by viewModel<SettingViewModel>()

    private val result =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (checkedOverlayPermission()) {
                startFloatingService()
            } else {
                binding.switchBackgroundSearch.isSelected = false
            }
        }

    override fun init() {
        setupToolbar()
        setupSwitch()
    }

    override fun observeViewModel() {
        viewModel.apply {

        }
    }

    private fun setupToolbar() {
        requireActivity().findViewById<LinearLayout>(R.id.toolbar_spinner_main).visibility = View.GONE
        requireActivity().findViewById<TextView>(R.id.toolbar_title_main).apply {
            visibility = VISIBLE
            text = "설정"
        }
    }

    private fun setupSwitch() {
        binding.switchBackgroundSearch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                if (checkedOverlayPermission()) showDialog()
                else startFloatingService()
            } else {
                stopFloatingService()
            }
        }
    }

    private fun startFloatingService() {
        startService(FloatingSearchButtonService::class.java)
    }

    private fun stopFloatingService() {
        stopService(FloatingSearchButtonService::class.java)
    }

    private fun showDialog() {
        AlertDialog.Builder(requireContext())
            .setMessage("검색을 이용하기 위해서는 권한이 필요해요! 어쩌구저쩌구 권한을 허용해주세여~")
            .setPositiveButton("확인") { _, _ ->
                Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:${requireContext().packageName}")
                ).apply { result.launch(this) }
            }.create()
    }

    private fun checkedOverlayPermission() =
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) !Settings.canDrawOverlays(requireContext())
        else false
}