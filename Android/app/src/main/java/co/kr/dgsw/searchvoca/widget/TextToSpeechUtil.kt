package co.kr.dgsw.searchvoca.widget

import com.google.cloud.texttospeech.v1.*
import com.google.protobuf.ByteString

object TextToSpeechUtil {
    fun getSpeechByteString(text: String, langCode: String): ByteString {
        val client = TextToSpeechClient.create()
        val request = SynthesizeSpeechRequest.newBuilder()
            .setInput(SynthesisInput.newBuilder().setText(text).build())
            .setAudioConfig(AudioConfig.newBuilder().setAudioEncoding(AudioEncoding.MP3).build())
            .setVoice(VoiceSelectionParams.newBuilder().setLanguageCode(langCode).build())
            .build()

        val response = client.synthesizeSpeech(request)
        return response.audioContent
    }

    fun formatToTTSLanguageCode(translateLangCode: String) =
        when (translateLangCode) {
            Translate.ENGLISH_US.langCode -> TextToSpeech.ENGLISH_US.langCode
            Translate.KOREAN.langCode -> TextToSpeech.KOREAN.langCode
            Translate.JAPANESE.langCode -> TextToSpeech.JAPANESE.langCode
            Translate.FRENCH.langCode -> TextToSpeech.FRENCH.langCode
            Translate.RUSSIAN.langCode -> TextToSpeech.RUSSIAN.langCode
            else -> {
                // todo 오류 토스트 날리기
                TextToSpeech.ENGLISH_US.langCode
            }
        }
}