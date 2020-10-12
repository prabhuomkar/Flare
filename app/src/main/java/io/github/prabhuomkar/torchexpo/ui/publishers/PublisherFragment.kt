package io.github.prabhuomkar.torchexpo.ui.publishers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.github.prabhuomkar.torchexpo.R

class PublisherFragment : Fragment() {

    private lateinit var viewModel: PublisherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.publisher_fragment, container, false)
    }

}