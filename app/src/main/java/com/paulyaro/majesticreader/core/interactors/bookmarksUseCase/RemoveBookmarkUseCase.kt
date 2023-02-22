package com.paulyaro.majesticreader.core.interactors.bookmarksUseCase

import com.paulyaro.majesticreader.core.data.bookmarksdData.BookmarkRepository
import com.paulyaro.majesticreader.core.domain.Bookmark
import com.paulyaro.majesticreader.core.domain.Document

class RemoveBookmarkUseCase (private val bookmarkRepository: BookmarkRepository) {
    suspend operator fun invoke (document: Document, bookmark: Bookmark) = bookmarkRepository.removeBookmark(document, bookmark)
}