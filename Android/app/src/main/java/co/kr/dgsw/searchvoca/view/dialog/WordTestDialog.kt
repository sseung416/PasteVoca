package co.kr.dgsw.searchvoca.view.dialog

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import co.kr.dgsw.searchvoca.databinding.DialogWordTestBinding
import co.kr.dgsw.searchvoca.view.activity.WordCheckActivity
import co.kr.dgsw.searchvoca.viewmodel.activity.CorrectionsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class WordTestDialog : DialogFragment() {
    private lateinit var binding: DialogWordTestBinding
    private val viewModel by viewModel<CorrectionsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogWordTestBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnNext.setOnClickListener {
            val (all, wrong, _) = viewModel.getList()
            val list = if (binding.radioAll.isChecked) all else wrong

            val intent = Intent(requireActivity(), WordCheckActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                .putExtra("list", list)
            requireActivity().startActivity(intent)
        }
    }
}