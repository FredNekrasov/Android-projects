package com.fred_projects.database

import android.app.Application
import androidx.room.Room
import com.fred_projects.education.rest_api.astronomy.model.json.GsonParser
import com.fred_projects.education.rest_api.astronomy.model.service.Converters
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideMainDB(app: Application): MainDB {
        return Room.databaseBuilder(app, MainDB::class.java, MainDB.MAIN_DB).allowMainThreadQueries()
            .addTypeConverter(Converters(GsonParser(Gson()))).build()
    }
}