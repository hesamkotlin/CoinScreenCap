package com.example.coinscreencap.di

import android.util.Log
import com.example.coinscreencap.BuildConfig
import com.example.coinscreencap.Constants
import com.example.coinscreencap.data.remote.AuthInterceptor
import com.example.coinscreencap.data.remote.NetworkDataSource
import com.example.coinscreencap.data.remote.WebService
import com.example.coinscreencap.domain.CryptoRepository
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module(
    includes= [
        DatabaseModule::class
    ]
)
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideApiService(
    ): WebService {
        val gson = GsonBuilder().setLenient().create()

        val httpLogger = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLogger)
            .addInterceptor(AuthInterceptor())
            .retryOnConnectionFailure(true)
            .build()


        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(WebService::class.java)
    }

    @Provides
    fun provideRepository(networkDataSource: NetworkDataSource) = CryptoRepository(networkDataSource)

}
