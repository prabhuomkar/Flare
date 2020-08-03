package io.github.prabhuomkar.torchexpo.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.prabhuomkar.torchexpo.databinding.MainFragmentBinding
import io.github.prabhuomkar.torchexpo.util.TensorUtil
import org.pytorch.Tensor

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
                binding.taskList.adapter = TaskListAdapter(tasks)
            } else {
                viewModel.getTasksFromNetwork()
            }
        })

        val tensor = Tensor.fromBlob(
            floatArrayOf(
                0.0701F, 0.6310F, 0.8519F, 0.5257F, 0.2673F,
                0.9703F, 0.2276F, 0.1715F, 0.5346F, 0.2133F, 0.8320F, 0.9678F
            ), longArrayOf(3, 2, 2)
        )
            .dataAsFloatArray
        val result = TensorUtil.argmax(tensor, 3, 2, 2)
        Log.v("TEST", result[0].toString())
        Log.v("TEST", result[1].toString())
        Log.v("TEST", result[2].toString())
        Log.v("TEST", result[3].toString())

        binding.refreshTaskList.setOnRefreshListener {
            viewModel.getTasksFromNetwork()
            binding.refreshTaskList.isRefreshing = false
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
