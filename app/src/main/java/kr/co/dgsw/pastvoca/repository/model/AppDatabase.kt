package kr.co.dgsw.pastvoca.repository.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.runBlocking
import kr.co.dgsw.pastvoca.repository.model.dao.VocabularyDao
import kr.co.dgsw.pastvoca.repository.model.dao.WordDao
import kr.co.dgsw.pastvoca.repository.model.dto.Vocabulary
import kr.co.dgsw.pastvoca.repository.model.dto.Word
import java.util.concurrent.Executor
import java.util.concurrent.Executors

@Database(entities = [Word::class, Vocabulary::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao
    abstract fun vocabularyDao(): VocabularyDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context, AppDatabase::class.java, "word.db")
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)

                            Executors.newSingleThreadExecutor().execute {
                                runBlocking {
                                    getInstance(context).apply {
                                        vocabularyDao().insert(Vocabulary(name = "전체"))
                                    }
                                }
                            }
                        }
                    }).build()
                INSTANCE = instance
                instance
            }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}