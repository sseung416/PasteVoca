package co.kr.searchvoca.di.module

import co.kr.searchvoca.data.repository.SearchRepositoryImpl
import co.kr.searchvoca.data.repository.VocabularyRepositoryImpl
import co.kr.searchvoca.data.repository.WordRepositoryImpl
import co.kr.searchvoca.domain.model.ErrorHandler
import co.kr.searchvoca.domain.repository.SearchRepository
import co.kr.searchvoca.domain.repository.VocabularyRepository
import co.kr.searchvoca.domain.repository.WordRepository
import co.kr.searchvoca.remote.ApiErrorHandlerImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<SearchRepository> { SearchRepositoryImpl(get()) }

    single<VocabularyRepository> { VocabularyRepositoryImpl(get()) }

    single<WordRepository> { WordRepositoryImpl(get()) }

    single<ErrorHandler> { ApiErrorHandlerImpl() }
}