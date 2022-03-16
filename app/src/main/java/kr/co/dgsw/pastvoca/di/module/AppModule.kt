package kr.co.dgsw.pastvoca.di.module

import kr.co.dgsw.pastvoca.repository.model.AppDatabase
import kr.co.dgsw.pastvoca.repository.model.repository.VocabularyRepository
import kr.co.dgsw.pastvoca.repository.model.repository.WordRepository
import kr.co.dgsw.pastvoca.viewmodel.activity.AddVocabularyViewModel
import kr.co.dgsw.pastvoca.viewmodel.activity.AddWordViewModel
import kr.co.dgsw.pastvoca.viewmodel.activity.MainViewModel
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
}