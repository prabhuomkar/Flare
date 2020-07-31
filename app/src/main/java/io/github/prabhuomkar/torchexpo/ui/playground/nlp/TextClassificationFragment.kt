package io.github.prabhuomkar.torchexpo.ui.playground.nlp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import io.github.prabhuomkar.torchexpo.databinding.TextClassificationFragmentBinding
import io.github.prabhuomkar.torchexpo.ui.playground.PlaygroundViewModel

class TextClassificationFragment : Fragment() {

    private val args: TextClassificationFragmentArgs by navArgs()
    private lateinit var viewModel: PlaygroundViewModel
    private var _binding: TextClassificationFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = TextClassificationFragmentBinding.inflate(inflater, container, false)
        viewModel =
            ViewModelProvider(this).get(
                PlaygroundViewModel(
                    this.activity!!.application
                )::class.java
            )

        binding.lifecycleOwner = viewLifecycleOwner
        binding.modelLoadedName = args.modelName

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}