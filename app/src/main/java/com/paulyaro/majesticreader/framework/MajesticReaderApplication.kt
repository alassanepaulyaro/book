package com.paulyaro.majesticreader.framework

import android.app.Application
import com.paulyaro.majesticreader.Interactors
import com.paulyaro.majesticreader.core.data.bookmarksdData.BookmarkRepository
import com.paulyaro.majesticreader.core.data.documentData.DocumentRepository
import com.paulyaro.majesticreader.core.interactors.bookmarksUseCase.AddBookmarkUseCase
import com.paulyaro.majesticreader.core.interactors.bookmarksUseCase.GetBookmarksUseCase
import com.paulyaro.majesticreader.core.interactors.bookmarksUseCase.RemoveBookmarkUseCase
import com.paulyaro.majesticreader.core.interactors.documentsUseCase.*
import com.paulyaro.majesticreader.framework.data.bookmarks.RoomBookmarkDataSource
import com.paulyaro.majesticreader.framework.data.documents.InMemoryOpenDocumentDataSource
import com.paulyaro.majesticreader.framework.data.documents.RoomDocumentDataSource
import com.paulyaro.majesticreader.framework.viewModel.MajesticViewModelFactory

class MajesticReaderApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val bookmarkRepository = BookmarkRepository(RoomBookmarkDataSource(this))
        val documentRepository = DocumentRepository(RoomDocumentDataSource(this), InMemoryOpenDocumentDataSource())

        MajesticViewModelFactory.inject(this, Interactors(
            AddBookmarkUseCase(bookmarkRepository),
            RemoveBookmarkUseCase(bookmarkRepository),
            GetBookmarksUseCase(bookmarkRepository),
            AddDocumentUseCase(documentRepository),
            RemoveDocumentUseCase(documentRepository),
            GetDocumentsUseCase(documentRepository),
            GetOpenDocumentUseCase(documentRepository),
            SetOpenDocumentUseCase(documentRepository)
        )
        )
    }
}