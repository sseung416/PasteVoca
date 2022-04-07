package co.kr.dgsw.searchvoca.datasource.remote.dao

import co.kr.dgsw.searchvoca.datasource.remote.dto.Res
import co.kr.dgsw.searchvoca.datasource.remote.dto.SearchWord
import retrofit2.http.GET
import retrofit2.http.Path

interface SearchService {
    @GET("/{word}")
    suspend fun getSearchResult(
        @Path("word") word: String
    ): Res<List<SearchWord>>
}