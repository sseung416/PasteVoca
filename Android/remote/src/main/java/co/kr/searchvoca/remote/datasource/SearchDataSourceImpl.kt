package co.kr.searchvoca.remote.datasource

import co.kr.searchvoca.data.datasouce.SearchDataSource
import co.kr.searchvoca.data.model.SearchResult
import co.kr.searchvoca.remote.request.DetectiveRequest
import co.kr.searchvoca.remote.request.TranslateRequest
import co.kr.searchvoca.remote.response.getLanguageCode
import co.kr.searchvoca.remote.util.requireSuccessfulBody
import co.kr.searchvoca.remote.response.toModel
import co.kr.searchvoca.remote.util.dictionaryApi
import co.kr.searchvoca.remote.util.translateApi
import co.kr.searchvoca.shared.domain.Translate
import co.kr.searchvoca.shared.domain.toTranslate

class SearchDataSourceImpl : SearchDataSource {
    override suspend fun loadDictionarySearchResults(word: String): List<SearchResult> =
        dictionaryApi.getDictionarySearchResult(word)
            .requireSuccessfulBody("loadDictionarySearchResult")
            .toModel()

    override suspend fun translateWord(word: String, tr: Translate): List<SearchResult> =
        translateApi.postTranslate(TranslateRequest(word, Translate.KOREAN.langCode, tr.langCode))
            .requireSuccessfulBody("translateWord")
            .toModel()

    override suspend fun detectLanguage(word: String): Translate? =
        translateApi.postDetective(DetectiveRequest(word))
            .requireSuccessfulBody("detectLanguage")
            .getLanguageCode()
            ?.toTranslate()
}