package co.kr.dgsw.searchvoca.di

import co.kr.dgsw.searchvoca.datasource.model.AppDatabase
import co.kr.dgsw.searchvoca.datasource.model.repository.VocabularyRepository
import co.kr.dgsw.searchvoca.datasource.model.repository.WordRepository
import co.kr.dgsw.searchvoca.datasource.remote.RetrofitInstance
import co.kr.dgsw.searchvoca.datasource.remote.repository.SearchRepository
import co.kr.dgsw.searchvoca.viewmodel.activity.*
import co.kr.dgsw.searchvoca.viewmodel.dialog.TextBottomSheetViewModel
import co.kr.dgsw.searchvoca.viewmodel.fragment.HomeViewModel
import co.kr.dgsw.searchvoca.viewmodel.fragment.SettingViewModel
import co.kr.dgsw.searchvoca.viewmodel.dialog.WordBottomSheetViewModel
import co.kr.dgsw.searchvoca.viewmodel.fragment.WordTestViewModel
import co.kr.dgsw.searchvoca.widget.coroutine.DispatcherProviderImpl
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val databaseModule = module {
    single { AppDatabase.getInstance(androidContext()) }
    single { get<AppDatabase>().vocabularyDao() }
    single { get<AppDatabase>().wordDao() }
}

val apiModule = module {
    single { RetrofitInstance.searchService }
}

val repositoryModule = module {
    single { VocabularyRepository(get()) }
    single { WordRepository(get()) }
    single { SearchRepository(get()) }
}

val dispatcherModule = module {
    single { DispatcherProviderImpl() }
}

val viewModelModule = module {
    // activity
    viewModel { MainViewModel() }
    viewModel { AddWordViewModel(get(), get(), get()) }
    viewModel { AddVocabularyViewModel(get(), get()) }
    viewModel { WordCheckViewModel(get(), get()) }
    viewModel { VocabularyViewModel(get(), get()) }
    viewModel { CorrectionsViewModel(get()) }

    // fragment
    viewModel { HomeViewModel(get(), get(), get()) }
    viewModel { SettingViewModel() }
    viewModel { WordTestViewModel() }

    // dialog
    viewModel { SearchResultViewModel(get(), get()) }
    viewModel { WordBottomSheetViewModel(get(), get()) }
    viewModel { SearchWordViewModel(get(), get()) }
    viewModel { TextBottomSheetViewModel() }
}