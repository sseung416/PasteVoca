package co.kr.dgsw.searchvoca.repository.remote.dao

import co.kr.dgsw.searchvoca.repository.remote.dto.SearchData
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    @GET("v1/search/encyc.json")
    suspend fun getSearchResult(
        @Query("query") query: String
    ): SearchData
}