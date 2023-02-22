package com.paulyaro.majesticreader.framework.data.documents

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface DocumentDao {
    @Insert(onConflict = REPLACE)
    suspend fun addDocument(documentEntity: DocumentEntity)

    @Query("SELECT *  FROM  document")
    suspend fun getDocument(): List<DocumentEntity>

    @Delete
    suspend fun removeDocument(documentEntity: DocumentEntity)
}