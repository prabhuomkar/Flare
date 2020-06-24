package io.github.prabhuomkar.torchexpo.ui.Model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.prabhuomkar.torchexpo.data.models.Model
import io.github.prabhuomkar.torchexpo.databinding.ModelListItemBinding

class ModelsListAdapter() :
    RecyclerView.Adapter<ModelsListAdapter.ModelViewHolder>() {

    private val models: List<Model> = listOf(
        Model(
            1,
            1,
            "ResNet",
            "Residual Networks by ResNet-50, ResNet-30",
            "", "", "", false, "imageUrl", 0
        ),
        Model(
            2,
            1,
            "GoogleNet",
            "By Google under Geoff, Deep NNs",
            "", "", "", false, "imageUrl", 0
        )
    )

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
        }
    }
}