package io.github.prabhuomkar.torchexpo.ui.playground.vision.objectdetection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import io.github.prabhuomkar.torchexpo.databinding.ObjectDetectionFragmentBinding

class ObjectDetectionFragment : Fragment() {

    private lateinit var viewModel: ObjectDetectionViewModel
    private var _binding: ObjectDetectionFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ObjectDetectionFragmentBinding.inflate(inflater, container, false)
        viewModel =
            ViewModelProvider(this).get(
                ObjectDetectionViewModel(this.activity!!.application)::class.java
            )

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}