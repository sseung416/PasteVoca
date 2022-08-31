package co.kr.searchvoca.shared.domain

import java.util.*

enum class Translate(val locale: Locale, val langCode: String) {

    KOREAN(Locale.KOREAN, "ko"),

    JAPANESE(Locale.JAPANESE, "ja"),

    FRENCH(Locale.FRENCH, "fr"),

    ENGLISH(Locale.ENGLISH, "en"),

    CHINESE(Locale.CHINESE, "zh");
}

/**
 * tts 지원 언어 목록 (구현한 것은 0 표시)
 * Korean [0]
 * Italian
 * German
 * Japanese [0]
 * French [0]
 * English [0]
 * Canada_French
 * Chinese [0]
 * Simplified_Chinese
 * Taiwan
 * Traditional Chinese
 * UK
 * US
 * */

fun String.toTranslate(): Translate? {
    val langCode = if (this.length != 2) {
        this.substring(0, 2)
    } else {
        this
    }

    return Translate.values().firstOrNull { it.langCode == langCode }
}