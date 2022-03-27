package co.kr.dgsw.searchvoca.widget

import android.content.Context
import android.widget.ArrayAdapter
import co.kr.dgsw.searchvoca.repository.model.dto.VocabularyName

class SpinnerAdapter(context: Context, resource: Int, objects: MutableList<String> = arrayListOf())
    : ArrayAdapter<String>(context, resource, objects) {

    private val list = arrayListOf<VocabularyName>()

    override fun clear() {
        super.clear()
        notifyDataSetChanged()
    }

    fun addAll(list: List<VocabularyName>) {
        this.list.clear()
        this.list.add(VocabularyName(null, "전체"))
        this.list.addAll(list)

        super.addAll(this.list.map { it.name })
        notifyDataSetChanged()
    }

    fun getVocabularyId(index: Int) = list[index].id
}