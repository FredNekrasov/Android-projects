package com.fred_projects.education.jumping_rope.di

import com.fred_projects.education.jumping_rope.model.verification.check_count.*
import com.fred_projects.education.jumping_rope.model.verification.check_date.*
import dagger.*
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface IJRVBinder {
    @Binds
    @Singleton
    fun bindJRCount(checkJRCount: CheckJRCount): ICheckJRCount
    @Binds
    @Singleton
    fun bindJRDate(checkJRDate: CheckJRDate): ICheckJRDate
}