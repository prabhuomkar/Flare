package io.github.prabhuomkar.torchexpo.ui.task

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.prabhuomkar.torchexpo.binding.BindingHandlers
import io.github.prabhuomkar.torchexpo.data.db.model.Model
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
            binding.handlers = BindingHandlers()
        }
    }
}