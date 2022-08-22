package co.kr.searchvoca.remote.api

import co.kr.searchvoca.remote.constant.Network
import co.kr.searchvoca.remote.request.DetectiveRequest
import co.kr.searchvoca.remote.request.TranslateRequest
import co.kr.searchvoca.remote.response.DetectiveResponse
import co.kr.searchvoca.remote.response.TranslateResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface TranslateApi {
    // https://cloud.google.com/translate/docs/basic/translate-text-basic#translate_translate_text-drest
    @POST(Network.TRANSLATE_POST_TRANSLATE)
    suspend fun postTranslate(
        @Body body: TranslateRequest
    ): Response<TranslateResponse>

    // https://cloud.google.com/translate/docs/basic/detecting-language?hl=ko#translate-detect-language-multiple-drest
    @POST(Network.TRANSLATE_POST_DETECTIVE)
    suspend fun postDetective(
        @Body body: DetectiveRequest
    ): Response<DetectiveResponse>
}