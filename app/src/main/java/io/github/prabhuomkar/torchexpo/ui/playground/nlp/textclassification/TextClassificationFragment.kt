package io.github.prabhuomkar.torchexpo.ui.playground.nlp.textclassification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import io.github.prabhuomkar.torchexpo.databinding.TextClassificationFragmentBinding

class TextClassificationFragment : Fragment() {

    private lateinit var viewModel: TextClassificationViewModel
    private var _binding: TextClassificationFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = TextClassificationFragmentBinding.inflate(inflater, container, false)
        viewModel =
            ViewModelProvider(this).get(
                TextClassificationViewModel(this.activity!!.application)::class.java
            )

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}