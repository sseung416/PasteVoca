package co.kr.searchvoca.ui.setting

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import co.kr.searchvoca.R
import co.kr.searchvoca.databinding.FragmentSettingBinding
import co.kr.searchvoca.ui.BindingFragment
import co.kr.searchvoca.ui.bind
import co.kr.searchvoca.ui.dialog.vocabulary.VocabularyBottomSheetDialog
import co.kr.searchvoca.ui.launchAndRepeatWithViewLifecycle
import co.kr.searchvoca.ui.setting.SettingNavigationAction.*
import co.kr.searchvoca.ui.setting.service.FloatingSearchButtonService
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class SettingFragment : BindingFragment<FragmentSettingBinding>(R.layout.fragment_setting) {

    private val viewModel by viewModel<SettingViewModel>()

    private val result =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            binding.switchSearch.isChecked = false

            if (checkOverlayPermission()) {
                Timber.d("Overlay permission is granted. Start FloatingSearchButtonService.")
                binding.switchSearch.isChecked = true
                startService(FloatingSearchButtonService::class.java)
            } else {
                Timber.w("Overlay permission is denied.")
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bind {
            vm = viewModel
        }

        observeState()
    }

    private fun observeState() {
        launchAndRepeatWithViewLifecycle {
            viewModel.navigationAction.collect { action ->
                when (action) {
                    ShowFrequencyListAction -> {}

                    ShowVocabularyListAction ->
                        VocabularyBottomSheetDialog({ vocabulary ->
                            viewModel.vocabulary.value = vocabulary
                        }).show(parentFragmentManager, "")

                    StartBackgroundSearchAction ->
                        if (checkOverlayPermission()) {
                            Timber.d("Overlay permission is granted. Start the FloatingSearchButtonService.")
                            startService(FloatingSearchButtonService::class.java)
                        } else {
                            Timber.d("Overlay permission is denied. Show the overlay permission request dialog.")
                            showOverlayPermissionRequestDialog()
                        }

                    StopBackgroundSearchAction -> {
                        Timber.d("Stop the FloatingSearchButtonService.")
                        stopService(FloatingSearchButtonService::class.java)
                    }
                }
            }
        }
    }

    private fun showOverlayPermissionRequestDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setMessage(getString(R.string.message_permission))
            .setPositiveButton(getString(R.string.confirm)) { _, _ ->
                // 세팅으로 이동
                val intent = Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:${requireContext().packageName}")
                )
                result.launch(intent)
            }.create()
            .show()
    }

    private fun checkOverlayPermission(): Boolean {
        val isGrantedOverlayPermission = Settings.canDrawOverlays(requireContext())
        Timber.d("isGrantedOverlayPermission=$isGrantedOverlayPermission")
        return isGrantedOverlayPermission
    }
}