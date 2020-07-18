package io.github.prabhuomkar.torchexpo.ui.playground.vision.imagesegmentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import io.github.prabhuomkar.torchexpo.data.db.model.Model
import io.github.prabhuomkar.torchexpo.databinding.ImageSegmentationFragmentBinding

class ImageSegmentationFragment : Fragment() {

    private val args: ImageSegmentationFragmentArgs by navArgs()
    private lateinit var viewModel: ImageSegmentationViewModel
    private var _binding: ImageSegmentationFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var _model: Model

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ImageSegmentationFragmentBinding.inflate(inflater, container, false)
        viewModel =
            ViewModelProvider(this).get(
                ImageSegmentationViewModel(this.activity!!.application)::class.java
            )

        binding.lifecycleOwner = viewLifecycleOwner

        val modelId = args.modelId
        viewModel.model(modelId).observe(viewLifecycleOwner, Observer { model ->
            if (model != null) {
                _model = model
                binding.model = model
            }
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}