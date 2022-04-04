package co.kr.dgsw.searchvoca.view.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import co.kr.dgsw.searchvoca.R
import co.kr.dgsw.searchvoca.databinding.FragmentSearchResultDialog2Binding
import co.kr.dgsw.searchvoca.widget.recyclerview.adapter.SearchWordAdapter

class SearchResultDialog2(definitionList: List<String>) : DialogFragment() {
    private lateinit var binding: FragmentSearchResultDialog2Binding
    private val adapter = SearchWordAdapter(definitionList)

    lateinit var callback: OnWordSelectedListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchResultDialog2Binding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvSearchResult.adapter = adapter
        adapter.onClickMeaningListener = {
            callback.onWordSelected(it)
            dismiss()
        }
    }

    fun setOnWordSelectedListener(callback: OnWordSelectedListener) {
        this.callback = callback
    }

    override fun onResume() {
        super.onResume()
        val width = resources.getDimensionPixelSize(R.dimen.search_dialog_width)
        val height = resources.getDimensionPixelSize(R.dimen.search_dialog_height)
        dialog?.window?.setLayout(width, height)
    }

    interface OnWordSelectedListener {
        fun onWordSelected(definition: String)
    }
}