package co.kr.searchvoca.data.datasouce

import co.kr.searchvoca.data.model.WordResult

interface WordDataSource {
    suspend fun loadWords(): List<WordResult>

    suspend fun loadSearchHistory(): List<WordResult>

    suspend fun loadWordsByVocabulary(vocabularyId: Int): List<WordResult>

    suspend fun getSearchHistoryCount(): Int

    suspend fun getWordCount(vocabularyId: Int): Int

    suspend fun createWord(wordResult: WordResult)

    suspend fun editWord(wordResult: WordResult)

    suspend fun changeWordVocabulary(ids: List<Int>, vocabularyId: Int)

    suspend fun deleteWord(wordResult: WordResult)

    suspend fun deleteWord(id: Int)

    suspend fun deleteWords(ids: List<Int>)
}