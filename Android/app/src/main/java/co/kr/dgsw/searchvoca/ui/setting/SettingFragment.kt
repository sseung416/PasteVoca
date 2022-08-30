package co.kr.dgsw.searchvoca.ui.setting

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import co.kr.dgsw.searchvoca.R
import co.kr.dgsw.searchvoca.databinding.FragmentSettingBinding
import co.kr.dgsw.searchvoca.ui.BindingFragment
import co.kr.dgsw.searchvoca.ui.bind
import co.kr.dgsw.searchvoca.ui.dialog.vocabulary.VocabularyBottomSheetDialog
import co.kr.dgsw.searchvoca.ui.launchAndRepeatWithViewLifecycle
import co.kr.dgsw.searchvoca.ui.setting.SettingNavigationAction.*
import co.kr.dgsw.searchvoca.ui.setting.service.FloatingSearchButtonService
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingFragment : BindingFragment<FragmentSettingBinding>(R.layout.fragment_setting) {

    private val viewModel by viewModel<SettingViewModel>()

    private val result =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            binding.switchSearch.isSelected = false

            if (checkedOverlayPermission()) {
                startService(FloatingSearchButtonService::class.java)
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
            viewModel.navigationAction.collect {
                when (it) {
                    ShowFrequencyListAction -> {}

                    ShowVocabularyListAction ->
                        VocabularyBottomSheetDialog({
                            viewModel.vocabulary.value = it
                        }).show(parentFragmentManager, "")

                    StartBackgroundSearchAction ->
                        if (checkedOverlayPermission()) showDialog()
                        else startService(FloatingSearchButtonService::class.java)

                    StopBackgroundSearchAction ->
                        stopService(FloatingSearchButtonService::class.java)
                }
            }
        }
    }

    private fun showDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setMessage(getString(R.string.message_permission))
            .setPositiveButton(getString(R.string.confirm)) { _, _ ->
                Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:${requireContext().packageName}")
                ).apply { result.launch(this) }
            }.create()
            .show()
    }

    private fun checkedOverlayPermission() =
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) !Settings.canDrawOverlays(requireContext())
        else false
}