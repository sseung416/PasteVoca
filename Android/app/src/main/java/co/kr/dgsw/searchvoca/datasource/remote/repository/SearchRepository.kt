package co.kr.dgsw.searchvoca.datasource.remote.repository

import co.kr.dgsw.searchvoca.datasource.remote.BaseRemote
import co.kr.dgsw.searchvoca.datasource.remote.dao.SearchService

class SearchRepository(override val service: SearchService) : BaseRemote<SearchService>() {
    suspend fun getSearchData(query: String) = service.getSearchResult(query)
}