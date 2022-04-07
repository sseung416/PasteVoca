package co.kr.dgsw.searchvoca.view.dialog

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.kr.dgsw.searchvoca.R
import co.kr.dgsw.searchvoca.databinding.DialogBottomSheetWordBinding
import co.kr.dgsw.searchvoca.datasource.model.dto.Word
import co.kr.dgsw.searchvoca.view.activity.AddWordActivity
import co.kr.dgsw.searchvoca.viewmodel.fragment.WordBottomSheetViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class WordBottomSheetDialog(
    private val word: Word
) : BottomSheetDialogFragment() {
    private val viewModel by sharedViewModel<WordBottomSheetViewModel>()
    private lateinit var binding: DialogBottomSheetWordBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogBottomSheetWordBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvEdit.setOnClickListener {
            val intent = Intent(requireActivity(), AddWordActivity::class.java)
                .putExtra("word", word)
            startActivity(intent)
            dismiss()
        }

        binding.tvDelete.setOnClickListener {
            viewModel.deleteWord(word)
            dismiss()
        }
    }

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    companion object {
        const val TAG = "WordBottomSheetDialog"
    }
}