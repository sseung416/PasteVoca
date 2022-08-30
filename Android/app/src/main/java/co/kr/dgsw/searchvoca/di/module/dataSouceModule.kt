package co.kr.dgsw.searchvoca.di.module

import co.kr.searchvoca.data.datasouce.SearchDataSource
import co.kr.searchvoca.data.datasouce.VocabularyDataSource
import co.kr.searchvoca.data.datasouce.WordDataSource
import co.kr.searchvoca.local.SearchVOCADatabase
import co.kr.searchvoca.local.datasource.VocabularyDataSourceImpl
import co.kr.searchvoca.local.datasource.WordDataSourceImpl
import co.kr.searchvoca.remote.datasource.SearchDataSourceImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataSourceModule = module {
    single<SearchDataSource> { SearchDataSourceImpl() }

    single<VocabularyDataSource> { VocabularyDataSourceImpl(get()) }

    single<WordDataSource> { WordDataSourceImpl(get()) }
}

val databaseModule = module {
    single {  SearchVOCADatabase.getInstance(androidContext(), CoroutineScope(SupervisorJob())) }
}