package co.kr.dgsw.searchvoca.di

import co.kr.dgsw.searchvoca.repository.model.AppDatabase
import co.kr.dgsw.searchvoca.repository.model.repository.VocabularyRepository
import co.kr.dgsw.searchvoca.repository.model.repository.WordRepository
import co.kr.dgsw.searchvoca.viewmodel.activity.*
import co.kr.dgsw.searchvoca.viewmodel.fragment.HomeViewModel
import co.kr.dgsw.searchvoca.viewmodel.fragment.SettingViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val databaseModule = module {
    single { AppDatabase.getInstance(androidContext()) }
    single { get<AppDatabase>().vocabularyDao() }
    single { get<AppDatabase>().wordDao() }
}

val repositoryModule = module {
    single { VocabularyRepository(get()) }
    single { WordRepository(get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get(), get()) }
    viewModel { AddWordViewModel(get()) }
    viewModel { AddVocabularyViewModel(get()) }
    viewModel { WordCheckViewModel() }
    viewModel { VocabularyViewModel(get()) }
    viewModel { HomeViewModel(get(), get()) }
    viewModel { SettingViewModel() }
}