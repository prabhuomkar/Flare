package io.github.prabhuomkar.torchexpo.ui.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.prabhuomkar.torchexpo.data.db.model.Task
import io.github.prabhuomkar.torchexpo.databinding.TaskFragmentBinding
import io.github.prabhuomkar.torchexpo.ui.Model.ModelListAdapter

class TaskFragment : Fragment() {

    private val args: TaskFragmentArgs by navArgs()
    private lateinit var viewModel: TaskViewModel
    private var _binding: TaskFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var _task: Task

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = TaskFragmentBinding.inflate(inflater, container, false)
        viewModel =
            ViewModelProvider(this).get(TaskViewModel(this.activity!!.application)::class.java)

        val linearLayoutManager = LinearLayoutManager(
            binding.root.context, RecyclerView.VERTICAL, false
        )
        binding.modelList.layoutManager = linearLayoutManager

        val taskId = args.taskId
        viewModel.task(taskId).observe(viewLifecycleOwner, Observer { task ->
            if (task != null) {
                _task = task
            }
        })
        viewModel.modelsByTask(taskId).observe(viewLifecycleOwner, Observer { models ->
            if (models != null) {
                binding.modelList.adapter = ModelListAdapter(models)
            }
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}