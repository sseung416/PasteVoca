package co.kr.dgsw.searchvoca.widget

enum class TextToSpeech(val langCode: String) {
    KOREAN("ko-KR"),
    ENGLISH_US("en-US"),
    JAPANESE("ja-JP"),
    FRENCH("fr-FR"),
    RUSSIAN("ru-RU")
}

enum class Translate(val langCode: String) {
    KOREAN("ko"),
    ENGLISH_US("en"),
    JAPANESE("ja"),
    FRENCH("fr"),
    RUSSIAN("ru")
}