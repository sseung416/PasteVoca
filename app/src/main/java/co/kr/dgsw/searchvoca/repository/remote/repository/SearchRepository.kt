package co.kr.dgsw.searchvoca.repository.remote.repository

import co.kr.dgsw.searchvoca.repository.remote.BaseRemote
import co.kr.dgsw.searchvoca.repository.remote.dao.SearchService

class SearchRepository(override val service: SearchService) : BaseRemote<SearchService>() {
    suspend fun getSearchData(query: String) = service.getSearchResult(query)
}