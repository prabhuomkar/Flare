package io.github.prabhuomkar.torchexpo.data.network

import io.github.prabhuomkar.torchexpo.ACCEPT_HEADER
import io.github.prabhuomkar.torchexpo.API_BASE_URL
import io.github.prabhuomkar.torchexpo.API_CONNECTION_TIMEOUT
import io.github.prabhuomkar.torchexpo.API_READ_TIMEOUT
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object APIClient {

    private val okHttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor { chain ->
            val original = chain.request()

            val request = original.newBuilder()
                .addHeader("Accept", ACCEPT_HEADER)
                .method(original.method, original.body)
                .build()

            chain.proceed(request)
        }.connectTimeout(API_CONNECTION_TIMEOUT, TimeUnit.MINUTES)
        .readTimeout(API_READ_TIMEOUT, TimeUnit.SECONDS).build()

    val instance: TorchExpoService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        retrofit.create(TorchExpoService::class.java)
    }

}