package com.fred_projects.education.main.di

import com.fred_projects.education.main.model.verification.check_date.*
import com.fred_projects.education.main.model.verification.check_image.*
import com.fred_projects.education.main.model.verification.check_lvl.*
import com.fred_projects.education.main.model.verification.check_mark.*
import com.fred_projects.education.main.model.verification.check_pw.*
import com.fred_projects.education.main.model.verification.check_student.*
import com.fred_projects.education.main.model.verification.check_variant.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ICheckBinder {
    @Binds
    @Singleton
    fun bindCheckPW(checkPW: CheckPW): ICheckPW
    @Binds
    @Singleton
    fun bindCheckStudent(checkStudent: CheckStudent): ICheckStudent
    @Binds
    @Singleton
    fun bindCheckVariant(checkVariant: CheckVariant): ICheckVariant
    @Binds
    @Singleton
    fun bindCheckLVL(checkLVL: CheckLVL): ICheckLVL
    @Binds
    @Singleton
    fun bindCheckDate(checkDate: CheckDate): ICheckDate
    @Binds
    @Singleton
    fun bindCheckMark(checkMark: CheckMark): ICheckMark
    @Binds
    @Singleton
    fun bindCheckImage(checkImage: CheckImage): ICheckImage
}