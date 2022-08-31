package co.kr.searchvoca.remote.util

import co.kr.searchvoca.remote.api.DictionaryApi
import co.kr.searchvoca.remote.api.TranslateApi
import co.kr.searchvoca.remote.constant.Network
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create

private val okHttpClient = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
    .build()

private val dictionaryBaseApi =
    Retrofit.Builder()
        .baseUrl(Network.DICTIONARY_BASE_URL)
        .addConverterFactory(gsonConverter)
        .client(okHttpClient)
        .build()

private val translateBaseApi =
    Retrofit.Builder()
        .baseUrl(Network.TRANSLATE_BASE_URL)
        .addConverterFactory(gsonConverter)
        .client(okHttpClient)
        .build()

internal val dictionaryApi = dictionaryBaseApi.create<DictionaryApi>()

internal val translateApi = translateBaseApi.create<TranslateApi>()