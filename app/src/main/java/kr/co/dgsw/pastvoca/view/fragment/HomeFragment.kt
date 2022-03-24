package kr.co.dgsw.pastvoca.view.fragment

import android.view.View
import android.widget.AdapterView
import kr.co.dgsw.pastvoca.base.BaseFragment
import kr.co.dgsw.pastvoca.databinding.FragmentHomeBinding
import kr.co.dgsw.pastvoca.view.activity.AddWordActivity
import kr.co.dgsw.pastvoca.viewmodel.fragment.HomeViewModel
import kr.co.dgsw.pastvoca.widget.SpinnerAdapter
import kr.co.dgsw.pastvoca.widget.extension.startActivity
import kr.co.dgsw.pastvoca.widget.livedata.EventObserver
import kr.co.dgsw.pastvoca.widget.recyclerview.adapter.WordAdapter
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