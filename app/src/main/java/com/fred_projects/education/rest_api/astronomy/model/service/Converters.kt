package com.fred_projects.education.rest_api.astronomy.model.service

import androidx.room.*
import com.fred_projects.education.rest_api.astronomy.model.json.JsonParser
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class Converters(private val jsonParser: JsonParser) {
    @TypeConverter
    fun fromHostJson(json: String): List<Host> = jsonParser.fromJson<ArrayList<Host>>(json, object : TypeToken<ArrayList<Host>>() {}.type) ?: emptyList()
    @TypeConverter
    fun toHostJson(json: List<Host>): String = jsonParser.toJson(json, object : TypeToken<ArrayList<Host>>() {}.type) ?: "[]"
}