package com.paulyaro.majesticreader.core.data.bookmarksdData

import com.paulyaro.majesticreader.core.domain.Bookmark
import com.paulyaro.majesticreader.core.domain.Document

class BookmarkRepository(private val dataSource: BookmarkDataSource) {
    suspend fun addBookmark(document: Document, bookmark: Bookmark) = dataSource.add(document,bookmark)
    suspend fun getBookmark(document: Document) = dataSource.read(document)
    suspend fun removeBookmark(document: Document, bookmark: Bookmark) = dataSource.remove(document, bookmark)
}