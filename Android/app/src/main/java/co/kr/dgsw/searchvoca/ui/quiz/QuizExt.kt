package co.kr.dgsw.searchvoca.ui.quiz

import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import co.kr.dgsw.searchvoca.R

fun Fragment.showQuitTestDialog(onPositiveButtonClicked: () -> Unit) {
    AlertDialog.Builder(requireContext())
        .setMessage(R.string.message_quit_test)
        .setPositiveButton(getString(R.string.yes)) { _, _ -> onPositiveButtonClicked.invoke() }
        .setNegativeButton(getString(R.string.no)) { dialog, _ -> dialog.dismiss() }
        .create()
        .show()
}

fun String.removeSpecialSymbol(): String =
    Regex("[^A-Za-z0-9]").replace(this, "")

fun String.removeWhiteSpaces(): String =
    this.filter { !it.isWhitespace() }