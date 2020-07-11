package io.github.prabhuomkar.torchexpo.ui.playground.audio.texttospeechsynthesis

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import io.github.prabhuomkar.torchexpo.databinding.TextToSpeechSynthesisFragmentBinding

class TextToSpeechSynthesisFragment : Fragment() {

    private lateinit var viewModel: TextToSpeechSynthesisViewModel
    private var _binding: TextToSpeechSynthesisFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = TextToSpeechSynthesisFragmentBinding.inflate(inflater, container, false)
        viewModel =
            ViewModelProvider(this).get(
                TextToSpeechSynthesisViewModel(this.activity!!.application)::class.java
            )

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}