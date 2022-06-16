package co.kr.dgsw.searchvoca.widget.extension

import java.util.*

fun String.formatToTTSLanguageCode(): Locale =
    when (this) {
        Translate.ENGLISH_US.langCode -> Locale.US
        Translate.KOREAN.langCode -> Locale.KOREA
        Translate.JAPANESE.langCode -> Locale.JAPAN
        Translate.FRENCH.langCode -> Locale.FRANCE
        else -> Locale.US
    }