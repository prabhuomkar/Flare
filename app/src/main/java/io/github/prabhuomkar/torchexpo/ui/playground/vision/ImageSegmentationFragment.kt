package io.github.prabhuomkar.torchexpo.ui.playground.vision

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import io.github.prabhuomkar.torchexpo.databinding.ImageSegmentationFragmentBinding
import io.github.prabhuomkar.torchexpo.util.FileUtil
import io.github.prabhuomkar.torchexpo.util.PlaygroundUtil

class ImageSegmentationFragment : Fragment() {

    private val args: ImageSegmentationFragmentArgs by navArgs()
    private lateinit var viewModel: VisionViewModel
    private var _binding: ImageSegmentationFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ImageSegmentationFragmentBinding.inflate(inflater, container, false)
        viewModel =
            ViewModelProvider(this).get(
                VisionViewModel(
                    this.activity!!.application
                )::class.java
            )

        binding.lifecycleOwner = viewLifecycleOwner
        binding.loadedModelName = args.modelName
        binding.viewModel = viewModel
        binding.actionChooseImage.setOnClickListener { PlaygroundUtil.chooseImage(this) }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        viewModel.clearPlayground()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK && intent != null && intent.data != null) {
            val imageUri = intent.data
            val bitmap = FileUtil.getBitmap(context, imageUri, false)
            binding.inputImage.setImageBitmap(bitmap)
            binding.loadedBitmap = imageUri
        }
    }
}