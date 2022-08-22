package co.kr.searchvoca.domain.repository

import co.kr.searchvoca.domain.model.Definition
import co.kr.searchvoca.shared.domain.Translate

interface SearchRepository {
    /**
     * 언어를 감지
     * @return 파라미터 값의 언어
     * */
    suspend fun detectLanguage(word: String): Translate

    /**
     * 한국어 사전 검색
     * */
    suspend fun loadDictionarySearchResults(word: String): List<Definition>

    /**
     * 한국어로 번역
     * @param source : [word]의 언어 값
     * */
    suspend fun translateWord(
        word: String,
        source: Translate
    ): List<Definition>
}