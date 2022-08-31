package co.kr.searchvoca.domain.usecase.search

import co.kr.searchvoca.domain.model.Definition
import co.kr.searchvoca.domain.model.ErrorHandler
import co.kr.searchvoca.domain.repository.SearchRepository
import co.kr.searchvoca.shared.domain.Translate
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import co.kr.searchvoca.domain.model.Result

class SearchWordUseCase(
    private val repo: SearchRepository,
    private val handler: ErrorHandler
) {

    operator fun invoke(word: String) = flow<Result<List<Definition>>> {
        val langCode = repo.detectLanguage(word)

        val definitions = if (langCode == Translate.KOREAN) {
            repo.loadDictionarySearchResults(word)
        } else {
            repo.translateWord(word, langCode!!)
        }

        emit(Result.Success(definitions))
    }.catch {
        emit(Result.Failure(handler.getError(it)))
    }
}