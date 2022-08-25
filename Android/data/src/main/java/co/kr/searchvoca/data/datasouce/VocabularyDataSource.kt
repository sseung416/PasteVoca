package co.kr.searchvoca.data.datasouce

import co.kr.searchvoca.data.model.VocabularyResult

interface VocabularyDataSource {
    suspend fun loadVocabularies(): List<VocabularyResult>

    suspend fun createVocabulary(vocabularyResult: VocabularyResult)

    suspend fun editVocabulary(vocabularyResult: VocabularyResult)

    suspend fun deleteVocabulary(vocabularyResult: VocabularyResult)
}