package com.fred_projects.education.main.content_provider

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import androidx.core.net.toUri
import com.fred_projects.education.main.model.entity.PracticalWork
import com.fred_projects.education.main.use_case.MainUseCases
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class MyContentProvider : ContentProvider() {
    class GetUseCases @Inject constructor(val useCases: MainUseCases)
    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface MyInterface {
        fun getUseCases(): GetUseCases
    }
    private val pwSF = MutableStateFlow<List<PracticalWork>>(emptyList())
    private val pwState = pwSF.asStateFlow()
    private val scope = CoroutineScope(Dispatchers.Default)
    private lateinit var db: MainUseCases
    override fun onCreate(): Boolean {
        val myInterface = EntryPointAccessors.fromApplication(context!!.applicationContext, MyInterface::class.java)
        db = myInterface.getUseCases().useCases
        getList()
        return true
    }
    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        val countOfPW = pwState.value.size
        if (selectionArgs != null) {
            val index = selectionArgs[0].toIntOrNull()
            if ((index != null) && (index in pwState.value.indices)) {
                scope.launch {
                    db.deleteData(
                        PracticalWork(
                            selectionArgs[0].toIntOrNull(),
                            selectionArgs[1], selectionArgs[2],
                            selectionArgs[3].toInt(),
                            selectionArgs[4].toInt(),
                            selectionArgs[5], selectionArgs[6].toInt(),
                            ""
                        )
                    )
                }
            }
        }
        getList()
        return countOfPW - pwState.value.size
    }

    override fun getType(uri: Uri): String = "fred_nekrasov.object/fred_nekrasov.practicalWorks"

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return if (values == null) null else {
            val int = setData(values)
            if (int != 0) null else "$URI_PREFIX/${pwState.value.size}".toUri()
        }
    }

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? = if ((selection != null) && (selectionArgs != null) && (sortOrder != null)) null else PWCursor(pwState.value.toList())

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int {
        if (values != null) {
            val index = values["id"] as Int?
            if ((index != null) && (index in pwState.value.indices)) setData(values)
        }
        return 0
    }
    private fun setData(values: ContentValues): Int {
        var int = 0
        val id = values["id"] as Int?
        val pwName = values["Practical Work"] as String
        val student = values["Student"] as String
        val variant = values["Variant"] as Int
        val lvl = values["Level"] as Int
        val date = values["Date"] as String
        val mark = values["Mark"] as Int
        scope.launch {
            int = db.addData(id, pwName, student, variant, lvl, date, mark, "")
        }
        return int
    }
    private fun getList() {
        db.getData().onEach {
            pwSF.emit(it)
        }.launchIn(scope)
    }
    companion object {
        const val URI_PREFIX = "content://fred_nekrasov/practicalWorks"
    }
}