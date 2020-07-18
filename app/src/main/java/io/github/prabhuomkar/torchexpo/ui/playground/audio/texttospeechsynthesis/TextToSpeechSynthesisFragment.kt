package io.github.prabhuomkar.torchexpo.ui.playground.audio.texttospeechsynthesis

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import io.github.prabhuomkar.torchexpo.data.db.model.Model
import io.github.prabhuomkar.torchexpo.databinding.TextToSpeechSynthesisFragmentBinding

class TextToSpeechSynthesisFragment : Fragment() {

    private val args: TextToSpeechSynthesisFragmentArgs by navArgs()
    private lateinit var viewModel: TextToSpeechSynthesisViewModel
    private var _binding: TextToSpeechSynthesisFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var _model: Model

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = TextToSpeechSynthesisFragmentBinding.inflate(inflater, container, false)
        viewModel =
            ViewModelProvider(this).get(
                TextToSpeechSynthesisViewModel(this.activity!!.application)::class.java
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