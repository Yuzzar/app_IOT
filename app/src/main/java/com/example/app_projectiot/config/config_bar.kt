package com.example.app_projectiot.config


import com.example.app_projectiot.model.Model_average
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


class config_bar {
    // set interceptor
    fun getInterceptor() : OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        return  okHttpClient
    }
    fun getRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://backendparkol-m77laoox7a-uc.a.run.app/")
            .client(getInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    fun getService() = getRetrofit().create(WaterReservoirGrafik::class.java)
}

interface WaterReservoirGrafik {
    @GET("average1")
    fun getDataWaterG(): Call<Model_average>
}
