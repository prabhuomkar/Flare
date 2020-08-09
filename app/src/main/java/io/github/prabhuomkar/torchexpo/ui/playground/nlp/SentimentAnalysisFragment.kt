package io.github.prabhuomkar.torchexpo.ui.playground.nlp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import io.github.prabhuomkar.torchexpo.databinding.SentimentAnalysisFragmentBinding

class SentimentAnalysisFragment : Fragment() {

    private val args: SentimentAnalysisFragmentArgs by navArgs()
    private lateinit var viewModel: NLPViewModel
    private var _binding: SentimentAnalysisFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SentimentAnalysisFragmentBinding.inflate(inflater, container, false)
        viewModel =
            ViewModelProvider(this).get(
                NLPViewModel(
                    this.activity!!.application
                )::class.java
            )

        binding.lifecycleOwner = viewLifecycleOwner
        binding.loadedModelName = args.modelName
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        viewModel.clearPlayground()
    }
}