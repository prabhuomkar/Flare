package io.github.prabhuomkar.torchexpo.ui.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.prabhuomkar.torchexpo.databinding.TaskFragmentBinding
import io.github.prabhuomkar.torchexpo.ui.Model.ModelListAdapter

class TaskFragment : Fragment() {

    private lateinit var viewModel: TaskViewModel
    private var _binding: TaskFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = TaskFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =
            ViewModelProvider(this).get(TaskViewModel(this.activity!!.application)::class.java)

        val linearLayoutManager = LinearLayoutManager(
            binding.root.context, RecyclerView.VERTICAL, false
        )
        binding.modelsListView.layoutManager = linearLayoutManager

        viewModel.models.observe(viewLifecycleOwner, Observer { models ->
            binding.modelsListView.adapter = ModelListAdapter(models)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}