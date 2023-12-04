package com.fred_projects.education.rest_api.astronomy.model.json

import java.lang.reflect.Type

interface JsonParser {
    fun<T> fromJson(json: String, type: Type): T?
    fun<T> toJson(obj: T, type: Type): String?
}