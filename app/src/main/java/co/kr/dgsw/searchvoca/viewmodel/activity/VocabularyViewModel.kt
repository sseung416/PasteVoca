package co.kr.dgsw.searchvoca.viewmodel.activity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import co.kr.dgsw.searchvoca.base.BaseViewModel
import co.kr.dgsw.searchvoca.repository.model.dto.VocabularyName
import co.kr.dgsw.searchvoca.repository.model.repository.VocabularyRepository
import co.kr.dgsw.searchvoca.widget.livedata.Event

class VocabularyViewModel(
    private val vocabularyRepository: VocabularyRepository
) : BaseViewModel() {
    val vocabularyNames = MutableLiveData<Event<List<VocabularyName>>>()

    fun getVocabularyNames() {
        viewModelScope.launch {
            vocabularyRepository.getVocabularyNames().apply {
                Log.e(TAG, "getVocabularyNames: $this", )
                vocabularyNames.postValue(Event(this))
            }
        }
    }

    companion object {
        private const val TAG = "VocabularyViewModel"
    }
}