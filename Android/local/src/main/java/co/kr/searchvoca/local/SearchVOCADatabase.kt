package co.kr.searchvoca.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import co.kr.searchvoca.local.dao.VocabularyDao
import co.kr.searchvoca.local.dao.WordDao
import co.kr.searchvoca.local.entity.VocabularyEntity
import co.kr.searchvoca.local.entity.WordEntity
import co.kr.searchvoca.shared.domain.VocabularyId.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(
    entities = [WordEntity::class, VocabularyEntity::class],
    version = 1,
    exportSchema = false
)
abstract class SearchVOCADatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao
    abstract fun vocabularyDao(): VocabularyDao

    companion object {
        @Volatile
        private var INSTANCE: SearchVOCADatabase? = null

        fun getInstance(context: Context, scope: CoroutineScope): SearchVOCADatabase =
            INSTANCE ?: synchronized(this) {
                val instance = Room
                    .databaseBuilder(context, SearchVOCADatabase::class.java, "db")
                    .addCallback(ItemCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

    // todo 잘 초기화되는 지 확인
    private class ItemCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            INSTANCE ?.let { database ->
                scope.launch {
                    database.vocabularyDao().apply {
                        insert(VocabularyEntity(SEARCH_HISTORY.ordinal, SEARCH_HISTORY.title))
                        insert(VocabularyEntity(NO_NAMED.ordinal, NO_NAMED.title))
                    }
                    database.wordDao().apply {
                        // todo 상수화
                        insert(WordEntity(NO_NAMED.ordinal, "Click This!", "클릭하면 뜻이 나와요!"))
                        insert(WordEntity(NO_NAMED.ordinal, "Long Click This ~", "길게 눌러보세요!"))
                    }
                }
            }
        }
    }
}