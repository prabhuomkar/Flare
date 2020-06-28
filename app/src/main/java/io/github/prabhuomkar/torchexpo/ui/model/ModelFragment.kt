package io.github.prabhuomkar.torchexpo.ui.model

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import io.github.prabhuomkar.torchexpo.data.model.Model
import io.github.prabhuomkar.torchexpo.databinding.ModelFragmentBinding
import io.github.prabhuomkar.torchexpo.utils.getPlaygroundDestinationAction

class ModelFragment : Fragment() {

    private lateinit var viewModel: ModelViewModel
    private var _binding: ModelFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var _model: Model

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ModelFragmentBinding.inflate(inflater, container, false)
        binding.modelSourceLink.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(_model.sourceLink)
                )
            )
        }
        binding.modelPaperLink.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(_model.paperLink)
                )
            )
        }
        binding.modelAction.setOnClickListener { v ->
            v.findNavController().navigate(getPlaygroundDestinationAction(_model.taskId))
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =
            ViewModelProvider(this).get(
                ModelViewModel(this.activity!!.application)::class.java
            )

        val modelId = arguments?.getInt("id")!!
        viewModel.model(modelId).observe(viewLifecycleOwner, Observer { model ->
            if (model != null) {
                _model = model
                binding.model = model
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}