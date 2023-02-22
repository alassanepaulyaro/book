package com.paulyaro.majesticreader.core.interactors.bookmarksUseCase

import com.paulyaro.majesticreader.core.data.bookmarksdData.BookmarkRepository
import com.paulyaro.majesticreader.core.domain.Document

class GetBookmarksUseCase(private val bookmarkRepository: BookmarkRepository) {
    suspend operator fun invoke(document: Document) = bookmarkRepository.getBookmark(document)
}