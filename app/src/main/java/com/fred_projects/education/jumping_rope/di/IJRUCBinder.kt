package com.fred_projects.education.jumping_rope.di

import com.fred_projects.education.jumping_rope.use_case.crud.create_update.AddJRRecord
import com.fred_projects.education.jumping_rope.use_case.crud.create_update.IAddJRRecord
import com.fred_projects.education.jumping_rope.use_case.crud.delete.DeleteJRRecord
import com.fred_projects.education.jumping_rope.use_case.crud.delete.IDeleteJRRecord
import com.fred_projects.education.jumping_rope.use_case.crud.read.GetJRData
import com.fred_projects.education.jumping_rope.use_case.crud.read.GetJRRecord
import com.fred_projects.education.jumping_rope.use_case.crud.read.IGetJRData
import com.fred_projects.education.jumping_rope.use_case.crud.read.IGetJRRecord
import dagger.Binds
import dagger.Module
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