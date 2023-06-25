package com.example.recipeapp.di

import com.example.recipeapp.common.Constants.BASE_URL
import com.example.recipeapp.common.Constants.CONNECTION_TIMEOUT
import com.example.recipeapp.common.Constants.READ_TIMEOUT
import com.example.recipeapp.common.Constants.WRITE_TIMEOUT
import com.example.recipeapp.data.RecipeApi
import com.example.recipeapp.data.RecipeRepositoryImpl
import com.example.recipeapp.domain.RecipeRepository
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {

    /* API */
    val gson = GsonBuilder()
        .setLenient()
        .create()

    val client = OkHttpClient.Builder().apply {
        connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)

        val logLevel = HttpLoggingInterceptor.Level.BODY
        addInterceptor(HttpLoggingInterceptor().setLevel(logLevel))
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client.build())
            .build()
            .create(RecipeApi::class.java)
    }
    single <RecipeRepository> { RecipeRepositoryImpl(get()) }

    /* UseCases */

    /* ViewModels */

}