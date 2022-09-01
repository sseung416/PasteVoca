package co.kr.searchvoca.di.module

import co.kr.searchvoca.ui.dialog.vocabulary.VocabularyBottomSheetViewModel
import co.kr.searchvoca.ui.history.SearchHistoryViewModel
import co.kr.searchvoca.ui.home.HomeViewModel
import co.kr.searchvoca.ui.quiz.QuizViewModel
import co.kr.searchvoca.ui.quiz.result.QuizResultViewModel
import co.kr.searchvoca.ui.quiz.listening.ListeningTestViewModel
import co.kr.searchvoca.ui.quiz.setting.QuizSettingViewModel
import co.kr.searchvoca.ui.setting.SettingViewModel
import co.kr.searchvoca.ui.vocabulary.UpdateVocabularyViewModel
import co.kr.searchvoca.ui.word.UpdateWordViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get(), get(), get(), get()) }

    viewModel { SearchHistoryViewModel(get(), get(), get()) }

    viewModel { QuizViewModel(get()) }

    viewModel { QuizSettingViewModel(get(), get()) }

    viewModel { ListeningTestViewModel() }

    viewModel { QuizResultViewModel() }

    viewModel { VocabularyBottomSheetViewModel(get()) }

    viewModel { SettingViewModel() }

    viewModel { UpdateWordViewModel(get(), get(), get(), get()) }

    viewModel { UpdateVocabularyViewModel(get(), get()) }
}
