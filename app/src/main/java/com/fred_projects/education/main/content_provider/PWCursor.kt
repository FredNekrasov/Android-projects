package com.fred_projects.education.main.content_provider

import android.database.AbstractCursor
import com.fred_projects.R
import com.fred_projects.education.main.model.PracticalWork

class PWCursor(private val pwList: List<PracticalWork>) : AbstractCursor() {
    override fun getCount(): Int = pwList.size

    override fun getColumnNames(): Array<String> {
        return arrayOf("id", "Practical Work", "Student", "Variant", "Level", "Date", "Mark")
    }

    override fun getString(p0: Int): String {
        return when(p0) {
            1 -> pwList[position].pwName
            2 -> pwList[position].student
            5 -> pwList[position].date
            else -> ""
        }
    }

    override fun getShort(p0: Int): Short {
        TODO("Not yet implemented")
    }

    override fun getInt(p0: Int): Int {
        return when(p0) {
            0 -> pwList[position].id!!
            3 -> pwList[position].variantNumber
            4 -> pwList[position].levelNumber
            6 -> pwList[position].mark
            else -> 0
        }
    }

    override fun getLong(p0: Int): Long {
        return when(p0) {
            0 -> pwList[position].id?.toLong()!!
            3 -> pwList[position].variantNumber.toLong()
            4 -> pwList[position].levelNumber.toLong()
            6 -> pwList[position].mark.toLong()
            else -> 0
        }
    }

    override fun getFloat(p0: Int): Float {
        TODO("Not yet implemented")
    }

    override fun getDouble(p0: Int): Double {
        TODO("Not yet implemented")
    }

    override fun isNull(p0: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun getType(column: Int): Int {
        return when(column) {
            0 -> FIELD_TYPE_INTEGER
            3 -> FIELD_TYPE_INTEGER
            4 -> FIELD_TYPE_INTEGER
            6 -> FIELD_TYPE_INTEGER
            else -> FIELD_TYPE_STRING
        }
    }
}