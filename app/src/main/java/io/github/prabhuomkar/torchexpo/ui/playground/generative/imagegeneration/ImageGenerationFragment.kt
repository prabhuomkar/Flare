package io.github.prabhuomkar.torchexpo.ui.playground.generative.imagegeneration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import io.github.prabhuomkar.torchexpo.databinding.ImageGenerationFragmentBinding

class ImageGenerationFragment : Fragment() {

    private lateinit var viewModel: ImageGenerationViewModel
    private var _binding: ImageGenerationFragmentBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ImageGenerationFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =
            ViewModelProvider(this).get(
                ImageGenerationViewModel(this.activity!!.application)::class.java
            )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}