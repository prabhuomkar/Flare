package io.github.prabhuomkar.torchexpo.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import io.github.prabhuomkar.torchexpo.R
import io.github.prabhuomkar.torchexpo.data.model.Task
import io.github.prabhuomkar.torchexpo.databinding.TaskListItemBinding


class TaskListAdapter(private val tasks: List<Task>) :
    RecyclerView.Adapter<TaskListAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TaskViewHolder(TaskListItemBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int = tasks.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) =
        holder.bind(tasks[position])

    inner class TaskViewHolder(val binding: TaskListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.task = task
            binding.root.setOnClickListener { v ->
                val bundle = bundleOf("id" to task.id)
                v.findNavController().navigate(R.id.action_mainFragment_to_taskFragment, bundle)
            }
        }
    }
}