package com.elitecodecamp.movies

import com.elitecodecamp.movies.data.network.MovieApiService
import com.elitecodecamp.movies.utils.LoggingInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    @Named("X-RapidAPI-Key")
    fun provideApiKey():String{
        return "569f636072msh864408946dc7541p151ec5jsn6e22ec94ce1c"
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor():LoggingInterceptor{
        return LoggingInterceptor()
    }
    @Provides
    @Singleton
    fun provideOkHttpClient(@Named("X-RapidAPI-Key") apiKey: String,loggingInterceptor: LoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor{
               val original = it.request()

               val request = original.newBuilder()
                   .header("X-RapidAPI-Key",apiKey)
                   .build()

                it.proceed(request)
            }
            .build()
    }


    @Provides
    @Singleton
    fun provideMovieApi(okHttpClient: OkHttpClient): MovieApiService {
        return Retrofit.Builder()
            .baseUrl("https://moviesdatabase.p.rapidapi.com/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }
}