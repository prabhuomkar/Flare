package io.github.prabhuomkar.torchexpo.ui.Model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.prabhuomkar.torchexpo.data.models.Model
import io.github.prabhuomkar.torchexpo.databinding.ModelListItemBinding

class ModelsListAdapter(private val models: List<Model>) :
    RecyclerView.Adapter<ModelsListAdapter.ModelViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ModelViewHolder(ModelListItemBinding.inflate(inflater))
    }

    override fun getItemCount(): Int = models.size

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) =
        holder.bind(models[position])

    inner class ModelViewHolder(val binding: ModelListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: Model) {
            binding.model = model
            binding.executePendingBindings()
        }
    }
}