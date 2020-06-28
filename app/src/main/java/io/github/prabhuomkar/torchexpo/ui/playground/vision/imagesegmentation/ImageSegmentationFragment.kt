package io.github.prabhuomkar.torchexpo.ui.playground.vision.imagesegmentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import io.github.prabhuomkar.torchexpo.databinding.ImageSegmentationFragmentBinding

class ImageSegmentationFragment : Fragment() {

    private lateinit var viewModel: ImageSegmentationViewModel
    private var _binding: ImageSegmentationFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ImageSegmentationFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =
            ViewModelProvider(this).get(
                ImageSegmentationViewModel(this.activity!!.application)::class.java
            )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}