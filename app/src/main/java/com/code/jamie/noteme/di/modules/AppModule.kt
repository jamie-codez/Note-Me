package com.code.jamie.noteme.di.modules

import android.app.Application
import androidx.room.Room
import com.code.jamie.noteme.NoteMe
import com.code.jamie.noteme.api.NoteMeService
import com.code.jamie.noteme.room.NoteMeBDCallback
import com.code.jamie.noteme.room.NoteMeDB
import com.code.jamie.noteme.room.NoteMeDao
import com.code.jamie.noteme.utils.SharedPrefsUtil
import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.GsonBuilder
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
class AppModule {
    @Singleton
    @Provides
    fun provideDatabase(app: Application): NoteMeDB {
        return Room.databaseBuilder(
            app,
            NoteMeDB::class.java,
            NoteMeDB.DB_NAME
        )
            .fallbackToDestructiveMigration()
            .addCallback(NoteMeBDCallback(app))
            .build()

    }

    @Singleton
    @Provides
    fun provideNoteMeService(): NoteMeService {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .readTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
        val gson = GsonBuilder().addSerializationExclusionStrategy(object : ExclusionStrategy {
            override fun shouldSkipClass(clazz: Class<*>?): Boolean {
                return false
            }

            override fun shouldSkipField(f: FieldAttributes?): Boolean {
                return true
            }
        }).create()
        return Retrofit.Builder()
            .baseUrl(getBaseUrl())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
            .create(NoteMeService::class.java)
    }

    @Singleton
    @Provides
    fun provideNoteMeDao(db: NoteMeDB): NoteMeDao {
        return db.noteMeDao()
    }

    @Singleton
    @Provides
    fun provideApplication(application: Application): Application {
        return application as NoteMe
    }

    @Singleton
    @Provides
    fun provideJWTPrefs(app: Application): String? =
        SharedPrefsUtil.getCurrentJWT(app.applicationContext, "jwt")

    companion object {
        fun getBaseUrl(): String = ""
    }
}