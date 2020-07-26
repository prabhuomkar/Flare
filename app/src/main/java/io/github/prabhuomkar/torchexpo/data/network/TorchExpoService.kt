package io.github.prabhuomkar.torchexpo.data.network

import io.github.prabhuomkar.torchexpo.data.db.model.Model
import io.github.prabhuomkar.torchexpo.data.db.model.Task
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TorchExpoService {

    @GET("tasks")
    suspend fun getTasks(): Response<List<Task>>

    @GET("tasks/{id}/models")
    suspend fun getModels(@Path("id") id: String): Response<List<Model>>
}