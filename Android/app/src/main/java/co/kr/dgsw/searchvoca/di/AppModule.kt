package co.kr.dgsw.searchvoca.di

import co.kr.dgsw.searchvoca.datasource.model.AppDatabase
import co.kr.dgsw.searchvoca.datasource.model.repository.CorrectionsWordRepository
import co.kr.dgsw.searchvoca.datasource.model.repository.VocabularyRepository
import co.kr.dgsw.searchvoca.datasource.model.repository.WordRepository
import co.kr.dgsw.searchvoca.datasource.remote.RetrofitInstance
import co.kr.dgsw.searchvoca.datasource.remote.repository.SearchRepository
import co.kr.dgsw.searchvoca.viewmodel.activity.*
import co.kr.dgsw.searchvoca.viewmodel.dialog.TestSettingViewModel
import co.kr.dgsw.searchvoca.viewmodel.dialog.VocabularyBottomSheetViewModel
import co.kr.dgsw.searchvoca.viewmodel.dialog.WordBottomSheetViewModel
import co.kr.dgsw.searchvoca.viewmodel.fragment.*
import co.kr.dgsw.searchvoca.widget.coroutine.DispatcherProviderImpl
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val databaseModule = module {
    single { AppDatabase.getInstance(androidContext()) }
    single { get<AppDatabase>().vocabularyDao() }
    single { get<AppDatabase>().wordDao() }
    single { get<AppDatabase>().correctionsWordDao() }
}

val apiModule = module {
    single { RetrofitInstance.searchService }
}

val repositoryModule = module {
    single { VocabularyRepository(get()) }
    single { WordRepository(get()) }
    single { SearchRepository(get()) }
    single { CorrectionsWordRepository(get()) }
}

val dispatcherModule = module {
    single { DispatcherProviderImpl() }
}

val viewModelModule = module {
    // activity
    viewModel { MainViewModel() }
    viewModel { UpdateWordViewModel(get(), get(), get(), get()) }
    viewModel { AddVocabularyViewModel(get(), get()) }
    viewModel { CardTestViewModel(get(), get(), get(), get()) }
    viewModel { CorrectionsViewModel(get(), get()) }
    viewModel { CorrectionsListViewModel(get(), get()) }

    // fragment
    viewModel { HomeViewModel(get(), get(), get()) }
    viewModel { SettingViewModel() }
    viewModel { WordTestViewModel(get(), get()) }
    viewModel { UpdateVocabularyViewModel(get(), get()) }

    // dialog
    viewModel { SearchResultViewModel(get(), get()) }
    viewModel { WordBottomSheetViewModel(get(), get()) }
    viewModel { SearchWordViewModel(get(), get()) }
    viewModel { VocabularyBottomSheetViewModel(get(), get()) }
    viewModel { TestSettingViewModel(get(), get()) }
}