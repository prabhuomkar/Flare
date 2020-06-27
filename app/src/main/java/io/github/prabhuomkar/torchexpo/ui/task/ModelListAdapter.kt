package io.github.prabhuomkar.torchexpo.ui.Model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import io.github.prabhuomkar.torchexpo.R
import io.github.prabhuomkar.torchexpo.data.model.Model
import io.github.prabhuomkar.torchexpo.databinding.ModelListItemBinding

class ModelListAdapter(private val models: List<Model>) :
    RecyclerView.Adapter<ModelListAdapter.ModelViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ModelViewHolder(ModelListItemBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int = models.size

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) =
        holder.bind(models[position])

    inner class ModelViewHolder(val binding: ModelListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: Model) {
            binding.model = model
            binding.root.setOnClickListener { v ->
                val bundle = bundleOf("id" to model.id)
                v.findNavController().navigate(R.id.action_taskFragment_to_modelFragment, bundle)
            }
        }
    }
}