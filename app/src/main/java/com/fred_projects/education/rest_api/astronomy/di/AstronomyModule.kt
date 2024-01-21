package com.fred_projects.education.rest_api.astronomy.di

import com.fred_projects.database.MainDB
import com.fred_projects.education.rest_api.astronomy.model.repository.IStarInfoRepository
import com.fred_projects.education.rest_api.astronomy.model.repository.StarInfoRepository
import com.fred_projects.education.rest_api.astronomy.model.service.IAstronomyService
import com.fred_projects.education.rest_api.astronomy.use_case.GetStarInfo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AstronomyModule {
    @Provides
    @Singleton
    fun provideAstronomyService(): IAstronomyService = Retrofit.Builder().baseUrl(IAstronomyService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(IAstronomyService::class.java)
    @Provides
    @Singleton
    fun provideStarRepository(api: IAstronomyService, db: MainDB): IStarInfoRepository = StarInfoRepository(api, db.astronomyDao)
    @Provides
    @Singleton
    fun provideGetStarInfo(repository: IStarInfoRepository) = GetStarInfo(repository)
}