package co.kr.dgsw.searchvoca.datasource.remote.repository

import com.google.cloud.translate.TranslateOptions

class DetectiveRepository {
    fun detectLanguage(text: String): String {
        val translate = TranslateOptions.getDefaultInstance().service
        return translate.detect(text).language
    }
}