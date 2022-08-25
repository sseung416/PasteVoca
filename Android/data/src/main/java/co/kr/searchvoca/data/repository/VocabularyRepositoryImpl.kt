package co.kr.searchvoca.data.repository

import co.kr.searchvoca.data.datasouce.VocabularyDataSource
import co.kr.searchvoca.data.model.VocabularyResult
import co.kr.searchvoca.data.model.toDomain
import co.kr.searchvoca.data.model.toModel
import co.kr.searchvoca.domain.model.Vocabulary
import co.kr.searchvoca.domain.repository.VocabularyRepository

class VocabularyRepositoryImpl(private val dataSource: VocabularyDataSource): VocabularyRepository {
    override suspend fun loadVocabularies(): List<Vocabulary> =
        dataSource.loadVocabularies().map(VocabularyResult::toDomain)

    override suspend fun createVocabulary(vocabulary: Vocabulary) =
        dataSource.createVocabulary(vocabulary.toModel())

    override suspend fun editVocabulary(vocabulary: Vocabulary) =
        dataSource.editVocabulary(vocabulary.toModel())

    override suspend fun deleteVocabulary(vocabulary: Vocabulary) =
        dataSource.deleteVocabulary(vocabulary.toModel())
}