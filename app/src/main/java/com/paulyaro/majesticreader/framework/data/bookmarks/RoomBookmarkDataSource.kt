package com.paulyaro.majesticreader.framework.data.bookmarks

import android.content.Context
import com.paulyaro.majesticreader.core.data.bookmarksdData.BookmarkDataSource
import com.paulyaro.majesticreader.core.domain.Bookmark
import com.paulyaro.majesticreader.core.domain.Document
import com.paulyaro.majesticreader.framework.data.MajesticReaderDatabase

class RoomBookmarkDataSource(context: Context) :BookmarkDataSource {

    private val bookmarkDao = MajesticReaderDatabase.getInstance(context).bookmarkDao()
    override suspend fun add(document: Document, bookmark: Bookmark) {
        bookmarkDao.addBookmark(BookmarkEntity(documentUri = document.url, page = bookmark.page))
    }

    override suspend fun read(document: Document) : List<Bookmark> =
        bookmarkDao.getBookmarks(document.url).map { Bookmark(it.id, it.page) }


    override suspend fun remove(document: Document, bookmark: Bookmark) =
        bookmarkDao.removeBookMark(BookmarkEntity(id = bookmark.id, documentUri = document.url, page = bookmark.page))
}