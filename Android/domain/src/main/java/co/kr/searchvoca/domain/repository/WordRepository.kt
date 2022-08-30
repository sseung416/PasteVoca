package co.kr.searchvoca.domain.repository

import co.kr.searchvoca.domain.model.Word

interface WordRepository {
    /**
     * 모든 단어 조회
     * */
    suspend fun loadWords(): List<Word>

    /**
     * 단어 검색 결과 조회
     * */
    suspend fun loadSearchHistory(): List<Word>

    /**
     * 검색 결과 갯수 조회
     * */
    suspend fun getSearchHistoryCount(): Int

    /**
     * 해당하는 단어장의 단어 갯수 조회
     *
     * @param vocabularyId 단어장 id
     * */
    suspend fun getWordCount(vocabularyId: Int): Int

    /**
     * 해당되는 단어장의 단어를 조회
     *
     * @param vocabularyId 단어장 id
     * */
    suspend fun loadWordsByVocabulary(vocabularyId: Int): List<Word>

    /**
     * 단어 생성
     * */
    suspend fun createWord(word: Word)

    /**
     * 단어 수정
     * */
    suspend fun editWord(word: Word)

    /**
     * 단어의 단어장(그룹)을 변경
     *
     * @param ids 수정할 단어의 id 리스트
     * @param vocabularyId 변경할 단어장의 id
     * */
    suspend fun changeWordVocabulary(ids: List<Int>, vocabularyId: Int)

    /**
     * 단어 삭제
     * */
    suspend fun deleteWord(word: Word)

    /**
     * 단어 삭제
     *
     * @param id 삭제할 단어의 id
     * */
    suspend fun deleteWord(id: Int)

    /**
     * 단어 여러 개 삭제
     *
     * @param ids 삭제할 단어들의 id 리스트
     * */
    suspend fun deleteWords(ids: List<Int>)
}