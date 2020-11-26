package com.example.mvvm_jetpack_masterclass.retrofit

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInstance {
    companion object {
        val BASE_URL: String = "https://jsonplaceholder.typicode.com"
        val interceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)

                    //default for all three is 10 seconds
                    //time to make connection with server
                .connectTimeout(30,TimeUnit.SECONDS)

                    //max gap between two data packets sent by server
                .readTimeout(20, TimeUnit.SECONDS)

                    //max gap between two datapackets sent to server
                .writeTimeout(25,TimeUnit.SECONDS)

        }.build()
        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }

    }
}