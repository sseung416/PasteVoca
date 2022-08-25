package co.kr.searchvoca.local.datasource

import co.kr.searchvoca.data.datasouce.VocabularyDataSource
import co.kr.searchvoca.data.model.VocabularyResult
import co.kr.searchvoca.local.SearchVOCADatabase
import co.kr.searchvoca.local.entity.toEntity
import co.kr.searchvoca.local.entity.toModel

class VocabularyDataSourceImpl(db: SearchVOCADatabase) : VocabularyDataSource {

    private val dao = db.vocabularyDao()

    override suspend fun loadVocabularies(): List<VocabularyResult> =
        dao.loadVocabularies().map { it.toModel() }

    override suspend fun createVocabulary(vocabularyResult: VocabularyResult) {
        dao.insert(vocabularyResult.toEntity())
    }

    override suspend fun editVocabulary(vocabularyResult: VocabularyResult) {
        dao.insert(vocabularyResult.toEntity())
    }

    override suspend fun deleteVocabulary(vocabularyResult: VocabularyResult) {
        dao.delete(vocabularyResult.toEntity())
    }
}