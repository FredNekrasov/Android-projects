package com.fred_projects.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fred_projects.database.dao.IAQDao
import com.fred_projects.database.dao.IAstronomyDao
import com.fred_projects.database.dao.IJRDao
import com.fred_projects.database.dao.IMainDao
import com.fred_projects.database.dao.IMathDao
import com.fred_projects.education.jumping_rope.model.JRReps
import com.fred_projects.education.main.model.PracticalWork
import com.fred_projects.education.rest_api.anime.model.AnimeQuotesEntity
import com.fred_projects.education.rest_api.astronomy.model.StarInfoEntity
import com.fred_projects.education.rest_api.astronomy.model.service.Converters
import com.fred_projects.education.rest_api.math.model.MathEntity
import javax.inject.Singleton

@Singleton
@Database(entities = [PracticalWork::class, JRReps::class, MathEntity::class, AnimeQuotesEntity::class, StarInfoEntity::class], version = 2)
@TypeConverters(Converters::class)
abstract class MainDB : RoomDatabase() {
    abstract val astronomyDao: IAstronomyDao
    abstract val aqDao: IAQDao
    abstract val mathDao: IMathDao
    abstract val jrDao: IJRDao
    abstract val mainDao: IMainDao
    companion object {
        const val MAIN_DB = "MainDB.db"
    }
}