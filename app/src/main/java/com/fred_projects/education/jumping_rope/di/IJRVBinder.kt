package com.fred_projects.education.jumping_rope.di

import com.fred_projects.education.jumping_rope.model.verification.check_count.CheckJRCount
import com.fred_projects.education.jumping_rope.model.verification.check_count.ICheckJRCount
import com.fred_projects.education.jumping_rope.model.verification.check_date.CheckJRDate
import com.fred_projects.education.jumping_rope.model.verification.check_date.ICheckJRDate
import dagger.Binds
import dagger.Module
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