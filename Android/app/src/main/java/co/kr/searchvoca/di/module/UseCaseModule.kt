package co.kr.searchvoca.di.module

import co.kr.searchvoca.domain.usecase.history.GetSearchHistoryCountUseCase
import co.kr.searchvoca.domain.usecase.history.LoadSearchHistoryUseCase
import co.kr.searchvoca.domain.usecase.search.DetectLanguageUseCase
import co.kr.searchvoca.domain.usecase.search.SearchWordUseCase
import co.kr.searchvoca.domain.usecase.vocabulary.CreateVocabularyUseCase
import co.kr.searchvoca.domain.usecase.vocabulary.DeleteVocabularyUseCase
import co.kr.searchvoca.domain.usecase.vocabulary.EditVocabularyUseCase
import co.kr.searchvoca.domain.usecase.vocabulary.LoadVocabulariesUseCase
import co.kr.searchvoca.domain.usecase.word.*
import org.koin.dsl.module

val searchUseCaseModule = module {
    single { DetectLanguageUseCase(get(), get()) }

    single { SearchWordUseCase(get(), get()) }
}

val vocabularyUseCaseModule = module {
    single { CreateVocabularyUseCase(get(), get()) }

    single { DeleteVocabularyUseCase(get(), get()) }

    single { EditVocabularyUseCase(get(), get()) }

    single { LoadVocabulariesUseCase(get(), get()) }
}

val wordUseCaseModule = module {
    single { CreateWordUseCase(get(), get()) }

    single { DeleteWordUseCase((get()), get()) }

    single { EditWordUseCase(get(), get()) }

    single { GetWordCountUseCase(get(), get()) }

    single { LoadWordsUseCase(get(), get()) }
}

val historyUseCase = module {
    single { GetSearchHistoryCountUseCase(get(), get()) }

    single { LoadSearchHistoryUseCase(get(), get()) }
}