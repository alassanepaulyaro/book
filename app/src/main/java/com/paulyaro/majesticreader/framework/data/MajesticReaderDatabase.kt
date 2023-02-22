package com.paulyaro.majesticreader.framework.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.paulyaro.majesticreader.framework.data.bookmarks.BookmarkDao
import com.paulyaro.majesticreader.framework.data.bookmarks.BookmarkEntity
import com.paulyaro.majesticreader.framework.data.documents.DocumentDao
import com.paulyaro.majesticreader.framework.data.documents.DocumentEntity

@Database(
    entities = [BookmarkEntity::class, DocumentEntity::class],
    version = 1, exportSchema = false
)
abstract class MajesticReaderDatabase : RoomDatabase() {

    companion object {

        private const val DATABASE_NAME = "reader.db"

        private var instance: MajesticReaderDatabase? = null

        private fun create(context: Context): MajesticReaderDatabase =
            Room.databaseBuilder(context, MajesticReaderDatabase::class.java, DATABASE_NAME)
                .build()


        fun getInstance(context: Context): MajesticReaderDatabase =
            (instance ?: create(context)).also { instance = it }
    }

    abstract fun bookmarkDao(): BookmarkDao

    abstract fun documentDao(): DocumentDao

}