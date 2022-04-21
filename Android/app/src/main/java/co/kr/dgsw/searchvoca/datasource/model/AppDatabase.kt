package co.kr.dgsw.searchvoca.datasource.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.runBlocking
import co.kr.dgsw.searchvoca.datasource.model.dao.VocabularyDao
import co.kr.dgsw.searchvoca.datasource.model.dao.WordDao
import co.kr.dgsw.searchvoca.datasource.model.dto.Vocabulary
import co.kr.dgsw.searchvoca.datasource.model.dto.Word
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
                                        initDatabase(this)
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

        private suspend fun initDatabase(appDatabase: AppDatabase) = appDatabase.apply {
            vocabularyDao().apply {
                insert(Vocabulary("검색한 단어", Vocabulary.VOCABULARY_ID_SEARCH))
                insert(Vocabulary("미정", Vocabulary.VOCABULARY_ID_NO_NAMED))
            }
            wordDao().apply {
                insert(Word(Vocabulary.VOCABULARY_ID_NO_NAMED, "Click This!", "클릭하면 뜻이 나와요!"))
                insert(Word(Vocabulary.VOCABULARY_ID_NO_NAMED, "Long Click This ~", "길게 눌러보세요!"))
            }
        }
    }
}