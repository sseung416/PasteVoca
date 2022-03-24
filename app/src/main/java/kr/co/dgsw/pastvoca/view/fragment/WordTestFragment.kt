package kr.co.dgsw.pastvoca.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.dgsw.pastvoca.R
import kr.co.dgsw.pastvoca.viewmodel.fragment.WordTestViewModel

class WordTestFragment : Fragment() {

    companion object {
        fun newInstance() = WordTestFragment()
    }

    private lateinit var viewModel: WordTestViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_word_test, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(WordTestViewModel::class.java)
        // TODO: Use the ViewModel
    }

}