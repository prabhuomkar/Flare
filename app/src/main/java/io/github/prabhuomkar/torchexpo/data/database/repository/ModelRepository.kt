package io.github.prabhuomkar.torchexpo.data.database.repository

import androidx.lifecycle.LiveData
import io.github.prabhuomkar.torchexpo.data.database.dao.ModelDao
import io.github.prabhuomkar.torchexpo.data.model.Model

class ModelRepository(private val modelDao: ModelDao) {
    val models: LiveData<List<Model>> = modelDao.getModels()
    fun model(id: Int): LiveData<Model> = modelDao.getModel(id)
    fun modelsByTask(taskId: Int): LiveData<List<Model>> = modelDao.getModelsByTaskId(taskId)
}