package co.kr.dgsw.searchvoca.widget.extension

import android.content.Intent
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import co.kr.dgsw.searchvoca.R

fun Fragment.startService(serviceClass: Class<*>) {
    requireActivity().startService(Intent(requireContext(), serviceClass))
}

fun Fragment.stopService(serviceClass: Class<*>) {
    requireActivity().stopService(Intent(requireContext(), serviceClass))
}

fun Fragment.showQuitTestDialog() {
    AlertDialog.Builder(requireContext())
        .setMessage(R.string.message_quit_test)
        .setPositiveButton(getString(R.string.yes)) { _, _ -> requireActivity().finish() }
        .setNegativeButton(getString(R.string.no)) { dialog, _ -> dialog.dismiss() }
        .create()
        .show()
}