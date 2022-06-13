package co.kr.dgsw.searchvoca.datasource.model.repository

import co.kr.dgsw.searchvoca.datasource.model.dao.VocabularyDao
import co.kr.dgsw.searchvoca.datasource.model.dto.Vocabulary

class VocabularyRepository(override val dao: VocabularyDao) : BaseRepository<VocabularyDao, Vocabulary>() {
    suspend fun getVocabularies() = dao.getVocabularies()

    suspend fun getCorrectionsVocabularies() = dao.getCorrectionsVocabularies()

    suspend fun getLastCorrectionsVocabularyId() = dao.getLastCorrectionsVocabularyId()

    suspend fun getVocabularyNames() = dao.getVocabularyNames()

    suspend fun getVocabularyNameById(id: Int) = dao.getVocabularyNameById(id)

    override suspend fun insert(obj: Vocabulary) = dao.insert(obj)

    override suspend fun update(obj: Vocabulary) = dao.update(obj)

    override suspend fun delete(obj: Vocabulary) = dao.delete(obj)
}