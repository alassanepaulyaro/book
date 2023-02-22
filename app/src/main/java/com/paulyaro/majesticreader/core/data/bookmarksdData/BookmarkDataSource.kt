package com.paulyaro.majesticreader.core.data.bookmarksdData

import com.paulyaro.majesticreader.core.domain.Bookmark
import com.paulyaro.majesticreader.core.domain.Document

interface BookmarkDataSource {
    suspend fun add(document: Document, bookmark: Bookmark)
    suspend fun read(document: Document): List<Bookmark>
    suspend fun remove(document: Document, bookmark: Bookmark)
}