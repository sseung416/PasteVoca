package co.kr.searchvoca.domain.repository

import co.kr.searchvoca.domain.model.Vocabulary

interface VocabularyRepository {
    /**
     * 모든 단어장 조회
     * */
    suspend fun loadVocabularies(): List<Vocabulary>

    /**
     * 단어장 조회
     *
     * @param id 조회할 단어장 id
     * */
    suspend fun loadVocabularyById(id: Int): Vocabulary

    /**
     * 단어장 생성
     * */
    suspend fun createVocabulary(vocabulary: Vocabulary)

    /**
     * 단어장 정보 수정
     * */
    suspend fun editVocabulary(vocabulary: Vocabulary)

    /**
     * 단어장 삭제
     * */
    suspend fun deleteVocabulary(vocabulary: Vocabulary)
}