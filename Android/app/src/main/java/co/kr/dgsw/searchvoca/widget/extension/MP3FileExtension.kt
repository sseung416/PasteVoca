package co.kr.dgsw.searchvoca.widget.extension

import android.content.Context
import android.media.MediaPlayer
import com.google.protobuf.ByteString
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

fun ByteString.saveToMP3File(context: Context, fileName: String) {
    context.openFileOutput(fileName, Context.MODE_PRIVATE).use {
        it.write(this.toByteArray())
    }
}

fun ByteString.playToMP3(context: Context) {
    val mp3File = File.createTempFile("kurchina", "mp3", context.cacheDir)
    mp3File.deleteOnExit()

    FileOutputStream(mp3File).use {
        it.write(this.toByteArray())
    }

    FileInputStream(mp3File).use {
        MediaPlayer().apply {
            setDataSource(it.fd)
            prepare()
            start()
        }
    }
}