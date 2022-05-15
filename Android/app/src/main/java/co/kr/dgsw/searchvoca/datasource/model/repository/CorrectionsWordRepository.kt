package co.kr.dgsw.searchvoca.datasource.model.repository

import co.kr.dgsw.searchvoca.datasource.model.dao.CorrectionsWordDao
import co.kr.dgsw.searchvoca.datasource.model.dto.CorrectionsWord

class CorrectionsWordRepository(override val dao: CorrectionsWordDao) : BaseRepository<CorrectionsWordDao, CorrectionsWord>() {
    suspend fun getCorrectionsWordByVocabularyId(vocabularyId: Int) = dao.getCorrectionsWordByVocabularyId(vocabularyId)

    override suspend fun insert(obj: CorrectionsWord) = dao.insert(obj)

    override suspend fun update(obj: CorrectionsWord) = dao.update(obj)

    override suspend fun delete(obj: CorrectionsWord) = dao.delete(obj)
}