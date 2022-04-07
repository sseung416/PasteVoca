package co.kr.dgsw.searchvoca.viewmodel.activity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import co.kr.dgsw.searchvoca.base.BaseViewModel
import co.kr.dgsw.searchvoca.datasource.model.dto.VocabularyName
import co.kr.dgsw.searchvoca.datasource.model.repository.VocabularyRepository
import co.kr.dgsw.searchvoca.widget.coroutine.DispatcherProviderImpl
import co.kr.dgsw.searchvoca.widget.livedata.Event

class VocabularyViewModel(
    dispatcherProvider: DispatcherProviderImpl,
    private val vocabularyRepository: VocabularyRepository
) : BaseViewModel(dispatcherProvider) {
    val vocabularyNames = MutableLiveData<Event<List<VocabularyName>>>()

    fun getVocabularyNames() = onIO {
        val res = vocabularyRepository.getVocabularyNames()
        Log.e(TAG, "getVocabularyNames: $res")
        vocabularyNames.postValue(Event(res))
    }

    companion object {
        private const val TAG = "VocabularyViewModel"
    }
}