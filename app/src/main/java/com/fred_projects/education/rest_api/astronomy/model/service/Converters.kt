package com.fred_projects.education.rest_api.astronomy.model.service

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.fred_projects.education.rest_api.astronomy.model.json.JsonParser
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class Converters(private val jsonParser: JsonParser) {
    @TypeConverter
    fun fromHostJson(json: String): List<Host> {
        return jsonParser.fromJson<ArrayList<Host>>(
            json,
            object : TypeToken<ArrayList<Host>>(){}.type
        ) ?: emptyList()
    }
    @TypeConverter
    fun toHostJson(json: List<Host>): String {
        return jsonParser.toJson(
            json,
            object : TypeToken<ArrayList<Host>>(){}.type
        ) ?: "[]"
    }
}