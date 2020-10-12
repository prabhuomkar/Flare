package io.github.prabhuomkar.torchexpo.ui.models

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.github.prabhuomkar.torchexpo.R

class ModelFragment : Fragment() {

    private lateinit var viewModel: ModelViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.model_fragment, container, false)
    }

}