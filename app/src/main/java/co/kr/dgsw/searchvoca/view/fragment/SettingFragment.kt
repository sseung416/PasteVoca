package co.kr.dgsw.searchvoca.view.fragment

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.activity.result.contract.ActivityResultContracts
import co.kr.dgsw.searchvoca.base.BaseFragment
import co.kr.dgsw.searchvoca.databinding.FragmentSettingBinding
import co.kr.dgsw.searchvoca.service.FloatingService
import co.kr.dgsw.searchvoca.viewmodel.fragment.SettingViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingFragment : BaseFragment<FragmentSettingBinding, SettingViewModel>() {
    override val viewModel by viewModel<SettingViewModel>()

    private val result =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (checkedOverlayPermission()) {
                startFloatingService()
            } else {
                binding.switchSetting.isSelected = false
            }
        }

    override fun init() {
        binding.switchSetting.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked) {
                if (checkedOverlayPermission()) {
                    showDialog()
                } else {
                    startFloatingService()
                }
            } else {
                stopFloatingService()
            }
        }
    }

    override fun observeViewModel() {}

    private fun startFloatingService() {
        requireActivity().startService(Intent(requireContext(), FloatingService::class.java))
    }

    private fun stopFloatingService() {
        //todo 이렇게 멈추는 거 맞는지 확인~
        requireActivity().stopService(Intent(requireContext(), FloatingService::class.java))
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