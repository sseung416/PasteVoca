package co.kr.searchvoca.remote.util

import com.google.gson.Gson
import retrofit2.converter.gson.GsonConverterFactory

private val gson = Gson().newBuilder().setLenient().create()

internal val gsonConverter = GsonConverterFactory.create(gson)