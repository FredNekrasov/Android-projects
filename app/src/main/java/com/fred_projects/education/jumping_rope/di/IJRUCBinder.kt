package com.fred_projects.education.jumping_rope.di

import com.fred_projects.education.jumping_rope.use_case.crud.create_update.*
import com.fred_projects.education.jumping_rope.use_case.crud.delete.*
import com.fred_projects.education.jumping_rope.use_case.crud.read.*
import dagger.*
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface IJRUCBinder {
    @Binds
    @Singleton
    fun bindAddJRRecord(addJRRecord: AddJRRecord):  IAddJRRecord
    @Binds
    @Singleton
    fun bindDeleteJRRecord(deleteJRRecord: DeleteJRRecord): IDeleteJRRecord
    @Binds
    @Singleton
    fun bindGetJRRecord(getJRRecord: GetJRRecord): IGetJRRecord
    @Binds
    @Singleton
    fun bindGetJRData(getJRData: GetJRData): IGetJRData
}