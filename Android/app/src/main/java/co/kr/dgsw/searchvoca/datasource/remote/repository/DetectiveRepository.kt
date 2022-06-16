package co.kr.dgsw.searchvoca.datasource.remote.repository

import android.content.res.Resources
import co.kr.dgsw.searchvoca.R
import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.translate.TranslateOptions

class DetectiveRepository {
    fun detectLanguage(resources: Resources, text: String): String {
        val inputStream = resources.openRawResource(R.raw.credential)
        val credentials = GoogleCredentials.fromStream(inputStream)
        val translateOptions = TranslateOptions.newBuilder().setCredentials(credentials).build()

        val translate = translateOptions.service
        return translate.detect(text).language
    }
}