package io.github.prabhuomkar.torchexpo.ui.playground.vision

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import io.github.prabhuomkar.torchexpo.databinding.ImageClassificationFragmentBinding
import io.github.prabhuomkar.torchexpo.ui.playground.PlaygroundViewModel

class ImageClassificationFragment : Fragment() {

    private val args: ImageClassificationFragmentArgs by navArgs()
    private lateinit var viewModel: PlaygroundViewModel
    private var _binding: ImageClassificationFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ImageClassificationFragmentBinding.inflate(inflater, container, false)
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