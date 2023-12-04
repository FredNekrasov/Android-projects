package com.fred_projects.education.rest_api.anime.di

import com.fred_projects.database.MainDB
import com.fred_projects.education.rest_api.anime.model.repository.AQRepository
import com.fred_projects.education.rest_api.anime.model.repository.IAQRepository
import com.fred_projects.education.rest_api.anime.model.service.IAQService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AQModule {
    @Provides
    @Singleton
    fun provideAQService(): IAQService {
        return Retrofit.Builder().baseUrl(IAQService.BASE_URL).
        addConverterFactory(GsonConverterFactory.create()).build().create(IAQService::class.java)
    }
    @Provides
    @Singleton
    fun provideAQRepository(api: IAQService, db: MainDB): IAQRepository {
        return AQRepository(api, db.aqDao)
    }
}