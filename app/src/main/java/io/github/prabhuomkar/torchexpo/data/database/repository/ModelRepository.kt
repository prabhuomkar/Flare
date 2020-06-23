package io.github.prabhuomkar.torchexpo.data.database.repository

import androidx.lifecycle.LiveData
import io.github.prabhuomkar.torchexpo.data.database.dao.ModelDao
import io.github.prabhuomkar.torchexpo.data.models.Model

class ModelRepository(private val modelDao: ModelDao) {
    val models: LiveData<List<Model>> = modelDao.getModels()
    fun modelsByTask(taskId: Int) = modelDao.getModelsByTaskId(taskId)
}