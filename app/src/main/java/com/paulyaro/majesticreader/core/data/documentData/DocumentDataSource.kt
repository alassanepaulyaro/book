package com.paulyaro.majesticreader.core.data.documentData

import com.paulyaro.majesticreader.core.domain.Document
import java.util.*

interface DocumentDataSource {
    suspend fun add(document: Document)
    suspend fun readAll(): List<Document>
    suspend fun remove(document: Document)
}