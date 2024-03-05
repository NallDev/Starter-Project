package com.nalldev.mystarterproject.di

import android.app.Application
import androidx.room.Room
import com.nalldev.mystarterproject.data.repository.StarterRepository
import com.nalldev.mystarterproject.data.repository.StarterRepositoryImpl
import com.nalldev.mystarterproject.data.source.local.StarterDb
import com.nalldev.mystarterproject.data.source.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(app: Application) = Room.databaseBuilder(
        app,
        StarterDb::class.java,
        "starter_db"
    )
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideStarterDao(db : StarterDb) = db.restaurantDao()

    @Provides
    @Singleton
    fun provideOkhttpClient() = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient) : Retrofit = Retrofit.Builder()
        .baseUrl("https://example.com")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) : ApiService = retrofit.create(ApiService::class.java)

    @Provides
    fun provideStarterRepository(apiService: ApiService) : StarterRepository = StarterRepositoryImpl(apiService)
}