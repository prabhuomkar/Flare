package io.github.prabhuomkar.torchexpo.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.prabhuomkar.torchexpo.data.models.Task
import io.github.prabhuomkar.torchexpo.databinding.TaskListItemBinding


class TasksListAdapter() :
    RecyclerView.Adapter<TasksListAdapter.TaskViewHolder>() {

    private val tasks: List<Task> = listOf(
        Task(
            1,
            "Image Classification",
            "Classify an image into several categories",
            "imageURL",
            "Vision"
        ),
        Task(
            2,
            "Image Segmentation",
            "Segmentation of an image into different intensity levels",
            "imageURL",
            "Vision"
        )
    )

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
        }
    }
}