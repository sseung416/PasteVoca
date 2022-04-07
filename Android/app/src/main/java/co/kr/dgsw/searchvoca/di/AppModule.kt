package co.kr.dgsw.searchvoca.di

import co.kr.dgsw.searchvoca.datasource.model.AppDatabase
import co.kr.dgsw.searchvoca.datasource.model.repository.VocabularyRepository
import co.kr.dgsw.searchvoca.datasource.model.repository.WordRepository
import co.kr.dgsw.searchvoca.datasource.remote.RetrofitInstance
import co.kr.dgsw.searchvoca.datasource.remote.repository.SearchRepository
import co.kr.dgsw.searchvoca.viewmodel.activity.*
import co.kr.dgsw.searchvoca.viewmodel.fragment.HomeViewModel
import co.kr.dgsw.searchvoca.viewmodel.fragment.SettingViewModel
import co.kr.dgsw.searchvoca.viewmodel.fragment.WordBottomSheetViewModel
import co.kr.dgsw.searchvoca.viewmodel.fragment.WordTestViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val databaseModule = module {
    single { AppDatabase.getInstance(androidContext()) }
    single { get<AppDatabase>().vocabularyDao() }
    single { get<AppDatabase>().wordDao() }
}

val serviceModule = module {
    single { RetrofitInstance.searchService }
}

val repositoryModule = module {
    single { VocabularyRepository(get()) }
    single { WordRepository(get()) }
    single { SearchRepository(get()) }
}

val viewModelModule = module {
    // activity
    viewModel { MainViewModel(get(), get()) }
    viewModel { AddWordViewModel(get(), get()) }
    viewModel { AddVocabularyViewModel(get()) }
    viewModel { WordCheckViewModel(get()) }
    viewModel { VocabularyViewModel(get()) }

    // fragment
    viewModel { HomeViewModel(get(), get()) }
    viewModel { SettingViewModel() }
    viewModel { WordTestViewModel() }

    // dialog
    viewModel { SearchResultViewModel(get()) }
    viewModel { WordBottomSheetViewModel(get()) }
}