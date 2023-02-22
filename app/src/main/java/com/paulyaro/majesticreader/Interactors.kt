package com.paulyaro.majesticreader

import com.paulyaro.majesticreader.core.interactors.bookmarksUseCase.AddBookmarkUseCase
import com.paulyaro.majesticreader.core.interactors.bookmarksUseCase.GetBookmarksUseCase
import com.paulyaro.majesticreader.core.interactors.bookmarksUseCase.RemoveBookmarkUseCase
import com.paulyaro.majesticreader.core.interactors.documentsUseCase.*

data class Interactors (val addBookmarkUseCase: AddBookmarkUseCase,
                        val deleteBookmarkUseCase: RemoveBookmarkUseCase,
                        val getBookmarksUseCase: GetBookmarksUseCase,
                        val addDocumentUseCase: AddDocumentUseCase,
                        val deleteDocumentUseCase: RemoveDocumentUseCase,
                        val getDocumentsUseCase: GetDocumentsUseCase,
                        val getOpenDocumentUseCase: GetOpenDocumentUseCase,
                        val SetOpenDocumentUseCase: SetOpenDocumentUseCase)