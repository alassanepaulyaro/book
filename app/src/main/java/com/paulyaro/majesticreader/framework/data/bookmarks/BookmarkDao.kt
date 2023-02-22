package com.paulyaro.majesticreader.framework.data.bookmarks

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface BookmarkDao {

    @Insert(onConflict = REPLACE)
    suspend fun addBookmark(bookmarkEntity: BookmarkEntity)

    @Query("SELECT *  FROM bookmark WHERE documentUri = :documentUri")
    suspend fun getBookmarks(documentUri: String): List<BookmarkEntity>

    @Delete
    suspend fun removeBookMark(bookmarkEntity: BookmarkEntity)
}