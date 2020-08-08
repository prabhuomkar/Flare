package io.github.prabhuomkar.torchexpo.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.prabhuomkar.torchexpo.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        viewModel =
            ViewModelProvider(this).get(MainViewModel(this.activity!!.application)::class.java)

        val linearLayoutManager = LinearLayoutManager(
            binding.root.context, RecyclerView.VERTICAL, false
        )
        binding.taskList.layoutManager = linearLayoutManager
        viewModel.tasks.observe(viewLifecycleOwner, Observer { tasks ->
            if (tasks != null && tasks.isNotEmpty()) {
                binding.showTasks = true
                binding.taskList.adapter = TaskListAdapter(tasks)
            } else {
                viewModel.getTasksFromNetwork(binding.root.context, null)
            }
        })

        binding.refreshTaskList.setOnRefreshListener {
            viewModel.getTasksFromNetwork(binding.root.context, binding.refreshTaskList)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
