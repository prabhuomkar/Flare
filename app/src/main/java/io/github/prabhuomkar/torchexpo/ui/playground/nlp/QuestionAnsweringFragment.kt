package io.github.prabhuomkar.torchexpo.ui.playground.nlp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import io.github.prabhuomkar.torchexpo.databinding.QuestionAnsweringFragmentBinding
import io.github.prabhuomkar.torchexpo.ui.playground.vision.VisionViewModel

class QuestionAnsweringFragment : Fragment() {

    private val args: QuestionAnsweringFragmentArgs by navArgs()
    private lateinit var viewModel: VisionViewModel
    private var _binding: QuestionAnsweringFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = QuestionAnsweringFragmentBinding.inflate(inflater, container, false)
        viewModel =
            ViewModelProvider(this).get(
                VisionViewModel(
                    this.activity!!.application
                )::class.java
            )

        binding.lifecycleOwner = viewLifecycleOwner
        binding.loadedModelName = args.modelName

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}