package co.kr.dgsw.searchvoca.datasource.remote

import co.kr.dgsw.searchvoca.datasource.remote.dao.SearchService
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    private val gson = Gson().newBuilder().setLenient().create()

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.0.17:8080/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build()

    val searchService = retrofit.create(SearchService::class.java)
}