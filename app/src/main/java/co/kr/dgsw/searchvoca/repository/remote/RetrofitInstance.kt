package co.kr.dgsw.searchvoca.repository.remote

import co.kr.dgsw.searchvoca.NAVER_CLIENT_ID
import co.kr.dgsw.searchvoca.NAVER_CLIENT_SECRET
import co.kr.dgsw.searchvoca.NAVER_SEARCH_BASE_URL
import co.kr.dgsw.searchvoca.repository.remote.dao.SearchService
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(KeyInterceptor())
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    private val gson = Gson().newBuilder().setLenient().create()

    private val retrofit = Retrofit.Builder()
        .baseUrl(NAVER_SEARCH_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build()

    val searchService = retrofit.create(SearchService::class.java)
}

class KeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("X-Naver-Client-Id", NAVER_CLIENT_ID)
            .addHeader("X-Naver-Client-Secret", NAVER_CLIENT_SECRET)
            .build()
        return chain.proceed(request)
    }
}