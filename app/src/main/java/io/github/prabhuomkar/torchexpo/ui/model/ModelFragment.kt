package io.github.prabhuomkar.torchexpo.ui.model

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.github.prabhuomkar.torchexpo.databinding.ModelFragmentBinding

class ModelFragment : Fragment() {

    private lateinit var viewModel: ModelViewModel
    private var _binding: ModelFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ModelFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}