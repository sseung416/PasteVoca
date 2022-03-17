package kr.co.dgsw.pastvoca.widget

import android.content.Context
import android.widget.ArrayAdapter

class SpinnerAdapter(context: Context, resource: Int, objects: MutableList<String>)
    : ArrayAdapter<String>(context, resource, objects) {

    override fun clear() {
        super.clear()
    }


}