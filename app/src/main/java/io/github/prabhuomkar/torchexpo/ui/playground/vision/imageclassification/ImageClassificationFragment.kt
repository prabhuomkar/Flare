package io.github.prabhuomkar.torchexpo.ui.playground.vision.imageclassification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import io.github.prabhuomkar.torchexpo.databinding.ImageClassificationFragmentBinding

class ImageClassificationFragment : Fragment() {

    private lateinit var viewModel: ImageClassificationViewModel
    private var _binding: ImageClassificationFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ImageClassificationFragmentBinding.inflate(inflater, container, false)
        viewModel =
            ViewModelProvider(this).get(
                ImageClassificationViewModel(this.activity!!.application)::class.java
            )

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}