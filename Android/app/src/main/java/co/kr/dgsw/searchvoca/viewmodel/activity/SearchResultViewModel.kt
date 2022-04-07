package co.kr.dgsw.searchvoca.viewmodel.activity

import android.util.Log
import co.kr.dgsw.searchvoca.base.BaseViewModel
import co.kr.dgsw.searchvoca.datasource.remote.repository.SearchRepository
import co.kr.dgsw.searchvoca.widget.coroutine.DispatcherProviderImpl

class SearchResultViewModel(
    dispatcherProvider: DispatcherProviderImpl,
    private val searchRepository: SearchRepository
) : BaseViewModel(dispatcherProvider) {
    fun getSearchResult(keyword: String) = onIO {
        Log.e("TAG", "getSearchResult: ${searchRepository.getSearchData(keyword)}", )
    }
}