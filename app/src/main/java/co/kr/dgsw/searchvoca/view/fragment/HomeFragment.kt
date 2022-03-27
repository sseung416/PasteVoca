package co.kr.dgsw.searchvoca.view.fragment

import android.view.View
import android.widget.AdapterView
import co.kr.dgsw.searchvoca.base.BaseFragment
import co.kr.dgsw.searchvoca.databinding.FragmentHomeBinding
import co.kr.dgsw.searchvoca.view.activity.AddWordActivity
import co.kr.dgsw.searchvoca.viewmodel.fragment.HomeViewModel
import co.kr.dgsw.searchvoca.widget.SpinnerAdapter
import co.kr.dgsw.searchvoca.widget.extension.startActivity
import co.kr.dgsw.searchvoca.widget.livedata.EventObserver
import co.kr.dgsw.searchvoca.widget.recyclerview.adapter.WordAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val viewModel by viewModel<HomeViewModel>()

    private val wordAdapter = WordAdapter()
    private lateinit var spinnerAdapter: SpinnerAdapter

    override fun init() {
        viewModel.getVocabularyNames()
        viewModel.getAllWords()

        spinnerAdapter = SpinnerAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item)

        wordAdapter.setList(listOf())

        binding.rvHome.adapter = wordAdapter
        binding.spinnerHome.apply {
            adapter = spinnerAdapter
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    when (val id = spinnerAdapter.getVocabularyId(position)) {
                        null -> viewModel.getAllWords()
                        else -> viewModel.getWordsByVocabulary(id)
                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {}
            }
        }

        binding.btnAddWordHome.setOnClickListener {
            requireActivity().startActivity(AddWordActivity::class.java)
        }
    }

    override fun observeViewModel() {
        viewModel.apply {
            vocabularyNames.observe(this@HomeFragment, EventObserver {
                spinnerAdapter.clear()
                spinnerAdapter.addAll(it)
            })

            allWords.observe(this@HomeFragment, EventObserver {
                wordAdapter.setList(it)
            })

            wordsByVoca.observe(this@HomeFragment, EventObserver {
                wordAdapter.setList(it)
            })
        }
    }
}