package co.kr.dgsw.searchvoca

import com.google.cloud.texttospeech.v1.*
import org.junit.Test
import java.io.FileOutputStream

class ExampleUnitTest {
    @Test
    fun ttsTest() {
        val client = TextToSpeechClient.create()
        val input = SynthesisInput.newBuilder().setText("앙녕").build()
        val voice = VoiceSelectionParams.newBuilder()
            .setLanguageCode("en-US")
            .setSsmlGender(SsmlVoiceGender.NEUTRAL)
            .build()
        val audioConfig = AudioConfig.newBuilder().setAudioEncoding(AudioEncoding.MP3).build()

        val response = client.synthesizeSpeech(input, voice, audioConfig)

        val audioContents = response.audioContent
        val out = FileOutputStream("output.mp3")
        out.write(audioContents.toByteArray())
    }
}