package co.kr.searchvoca.ui.setting

import android.content.Intent
import androidx.fragment.app.Fragment

fun Fragment.startService(serviceClass: Class<*>) {
    requireActivity().startService(Intent(requireContext(), serviceClass))
}

fun Fragment.stopService(serviceClass: Class<*>) {
    requireActivity().stopService(Intent(requireContext(), serviceClass))
}