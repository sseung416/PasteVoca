package kr.co.dgsw.pastvoca.viewmodel.activity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kr.co.dgsw.pastvoca.base.BaseViewModel
import kr.co.dgsw.pastvoca.repository.model.dto.VocabularyName
import kr.co.dgsw.pastvoca.repository.model.repository.VocabularyRepository
import kr.co.dgsw.pastvoca.widget.livedata.Event

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