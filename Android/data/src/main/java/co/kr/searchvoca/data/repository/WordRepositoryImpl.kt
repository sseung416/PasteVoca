package co.kr.searchvoca.data.repository

import co.kr.searchvoca.data.datasouce.WordDataSource
import co.kr.searchvoca.data.model.WordResult
import co.kr.searchvoca.data.model.toDomain
import co.kr.searchvoca.data.model.toModel
import co.kr.searchvoca.domain.model.Word
import co.kr.searchvoca.domain.repository.WordRepository

class WordRepositoryImpl(private val dataSource: WordDataSource): WordRepository {

    override suspend fun loadWords(): List<Word> =
        dataSource.loadWords().map(WordResult::toDomain)

    override suspend fun loadSearchHistory(): List<Word> =
        dataSource.loadSearchHistory().map(WordResult::toDomain)

    override suspend fun getSearchHistoryCount(): Int =
        dataSource.getSearchHistoryCount()

    override suspend fun getWordCount(vocabularyId: Int): Int =
        dataSource.getWordCount(vocabularyId)

    override suspend fun loadWordsByVocabulary(vocabularyId: Int): List<Word> =
        dataSource.loadWordsByVocabulary(vocabularyId).map(WordResult::toDomain)

    override suspend fun createWord(word: Word) =
        dataSource.createWord(word.toModel())

    override suspend fun editWord(word: Word) =
        dataSource.editWord(word.toModel())

    override suspend fun changeWordVocabulary(ids: List<Int>, vocabularyId: Int) {
        dataSource.changeWordVocabulary(ids, vocabularyId)
    }

    override suspend fun deleteWord(word: Word) =
        dataSource.deleteWord(word.toModel())

    override suspend fun deleteWord(id: Int) =
        dataSource.deleteWord(id)

    override suspend fun deleteWords(ids: List<Int>) =
        dataSource.deleteWords(ids)
}