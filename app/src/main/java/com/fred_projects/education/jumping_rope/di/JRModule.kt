package com.fred_projects.education.jumping_rope.di

import com.fred_projects.database.MainDB
import com.fred_projects.education.jumping_rope.model.repository.*
import com.fred_projects.education.jumping_rope.use_case.JRUseCases
import com.fred_projects.education.jumping_rope.use_case.crud.create_update.IAddJRRecord
import com.fred_projects.education.jumping_rope.use_case.crud.delete.IDeleteJRRecord
import com.fred_projects.education.jumping_rope.use_case.crud.read.*
import dagger.*
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object JRModule {
    @Provides
    @Singleton
    fun provideJRRepository(db: MainDB): IJRRepository = JRRepository(db.jrDao)
    @Provides
    @Singleton
    fun provideJRUseCases(
        addJRRecord: IAddJRRecord,
        deleteJRRecord: IDeleteJRRecord,
        getJRRecord: IGetJRRecord,
        getJRData: IGetJRData
    ): JRUseCases = JRUseCases(addJRRecord, deleteJRRecord, getJRRecord, getJRData)
}