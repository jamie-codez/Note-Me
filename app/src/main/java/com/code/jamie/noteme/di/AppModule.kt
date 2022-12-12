package com.code.jamie.noteme.di

import android.app.Application
import android.content.Context
import com.code.jamie.noteme.NoteMe
import com.code.jamie.noteme.api.NoteMeRepo
import com.code.jamie.noteme.api.NoteMeService
import com.code.jamie.noteme.db.NoteMeDB
import com.code.jamie.noteme.db.NoteMeDao
import com.code.jamie.noteme.db.RoomRepo
import com.code.jamie.noteme.ui.viewmodels.RoomViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val baseUrl = ""

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideNoteMeService(retrofit: Retrofit): NoteMeService =
        retrofit.create(NoteMeService::class.java)

    @Singleton
    @Provides
    fun provideNoteMeDB(context: Context): NoteMeDB =
        NoteMeDB.getInstance(context)

    @Singleton
    @Provides
    fun provideNoteMeDao(noteMeDB: NoteMeDB): NoteMeDao =
        noteMeDB.noteMeDao()

    @Singleton
    @Provides
    fun provideContext(application: Application):Context=
        application.applicationContext

//    @Singleton
//    @Provides
//    fun provideApplication(): Application =
//        NoteMe()
    @Singleton
    @Provides
    fun provideNoteMeRepo(noteMeService:NoteMeService):NoteMeRepo =
        NoteMeRepo(noteMeService)

    @Singleton
    @Provides
    fun provideRoomRepo(noteMeDao: NoteMeDao):RoomRepo=
        RoomRepo(noteMeDao)
    @Singleton
    @Provides
    fun provideRoomViewModel(roomRepo:RoomRepo,application: Application):RoomViewModel =
        RoomViewModel(roomRepo,application)


    private fun client(): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.MILLISECONDS)
            .callTimeout(60, TimeUnit.MILLISECONDS)
            .readTimeout(60, TimeUnit.MILLISECONDS)
            .writeTimeout(60, TimeUnit.MILLISECONDS)
            .addInterceptor(interceptor())
            .build()


    private fun interceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.HEADERS)
            setLevel(HttpLoggingInterceptor.Level.BODY)
            setLevel(HttpLoggingInterceptor.Level.BASIC)
        }
}