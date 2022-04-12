package co.kr.dgsw.searchvoca.view.dialog

import android.view.LayoutInflater
import android.view.ViewGroup
import co.kr.dgsw.searchvoca.R
import co.kr.dgsw.searchvoca.base.BaseDialog
import co.kr.dgsw.searchvoca.databinding.DialogSearchResultBinding
import co.kr.dgsw.searchvoca.widget.view.adapter.SearchWordAdapter

class SearchResultDialog(definitionList: List<String>) : BaseDialog<DialogSearchResultBinding>() {
    private val adapter = SearchWordAdapter(definitionList)
    lateinit var callback: OnWordSelectedListener

    override fun init() {
        setRecyclerView()
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): DialogSearchResultBinding = DialogSearchResultBinding.inflate(inflater)

    override fun getDialogSize(): Pair<Int, Int>? {
        val width = resources.getDimensionPixelSize(R.dimen.search_dialog_width)
        val height = resources.getDimensionPixelSize(R.dimen.search_dialog_height)
        return Pair(width, height)
    }

    private fun setRecyclerView() {
        binding.rvSearchResult.adapter = adapter
        adapter.onClickMeaningListener = {
            callback.onWordSelected(it)
            dismiss()
        }
    }

    fun setOnWordSelectedListener(callback: OnWordSelectedListener) {
        this.callback = callback
    }

    interface OnWordSelectedListener {
        fun onWordSelected(definition: String)
    }
}