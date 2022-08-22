package co.kr.searchvoca.remote.api

import co.kr.searchvoca.remote.response.DictionaryResponse
import co.kr.searchvoca.remote.constant.APIKey
import co.kr.searchvoca.remote.constant.Network
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DictionaryApi {
    @GET(Network.DICTIONARY_GET_SEARCH_RESULT)
    suspend fun getDictionarySearchResult(
        @Query("q") keyword: String,
        @Query("key") key: String = APIKey.DICTIONARY_AUTH
    ): Response<DictionaryResponse>
}