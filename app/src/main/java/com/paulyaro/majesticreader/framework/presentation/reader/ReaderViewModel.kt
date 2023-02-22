package com.paulyaro.majesticreader.framework.presentation.reader

import android.app.Application
import android.graphics.pdf.PdfRenderer
import android.net.Uri
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.paulyaro.majesticreader.Interactors
import com.paulyaro.majesticreader.core.domain.Bookmark
import com.paulyaro.majesticreader.core.domain.Document
import com.paulyaro.majesticreader.framework.viewModel.MajesticViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOException

class ReaderViewModel (application: Application, interactors: Interactors) : MajesticViewModel(application, interactors) {

    companion object {
        private const val DOCUMENT_ARG = "docuement"
        fun createArguments(document: Document) = bundleOf( DOCUMENT_ARG to document)
    }

    val document = MutableLiveData<Document>()
    val bookmarks = MediatorLiveData<List<Bookmark>>().apply {
        addSource (document) { document ->
            GlobalScope.launch {
                postValue(interactors.getBookmarksUseCase(document))
            }
        }
    }

    val currentPage = MediatorLiveData<PdfRenderer.Page>()
    val hasPreviousPage : LiveData<Boolean> = Transformations.map(currentPage) {
        it.index > 0
    }

    val hasNextPage: LiveData<Boolean> = Transformations.map(currentPage) {
        renderer.value?.let { renderer -> it.index < renderer.pageCount - 1 }
    }

    val renderer = MediatorLiveData<PdfRenderer>().apply {
        addSource(document) {
            try {
                val pdfRenderer = PdfRenderer(getFileDescriptor(Uri.parse(it.url))!!)
                value = pdfRenderer
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }


    val isBookmarked = MediatorLiveData<Boolean>().apply {
        addSource(document) { value = isCurrentPageBookmarked() }
        addSource(currentPage) { value = isCurrentPageBookmarked() }
        addSource(bookmarks) { value = isCurrentPageBookmarked() }
    }

    private fun isCurrentPageBookmarked() = bookmarks.value?.any(){
        it.page == currentPage.value?.index
    } == true

    val isInLibrary: MediatorLiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        addSource(document) {
            GlobalScope.launch {
                postValue(isInLibrary(it))
            }
        }
    }

    private suspend fun isInLibrary(document: Document?) =
        interactors.getDocumentsUseCase().any{ it.url == document?.url }


    private fun getFileDescriptor(uri: Uri) = application.contentResolver.openFileDescriptor(uri, "r")

    private fun isCurrentPageBookMarked() = bookmarks.value?.any {
        it.page == currentPage.value?.index
    } == true

    fun loadArguments(arguments : Bundle?) {
        if (arguments == null) {
            return
        }
        currentPage.apply {
            addSource(renderer) {
                renderer ->
                GlobalScope.launch{
                    val document = document.value
                    if (document != null) {
                        val bookmarks = interactors.getBookmarksUseCase(document).lastOrNull()?.page?:0
                        postValue(renderer.openPage(bookmarks))
                    }
                }
            }
        }
        val documentFromArguments = arguments.get(DOCUMENT_ARG) as Document?  ?: Document.EMPTY
        val lastOpenDocument = interactors.getOpenDocumentUseCase()
        document.value =  when {
            documentFromArguments != Document.EMPTY -> documentFromArguments
            documentFromArguments == Document.EMPTY && lastOpenDocument != Document.EMPTY -> lastOpenDocument
            else -> Document.EMPTY
        }
        document.value?.let { interactors.SetOpenDocumentUseCase(it)}
    }

    fun openDocument(uri: Uri) {
        document.value = Document(uri.toString(), "",0,"")
        document.value?.let{interactors.SetOpenDocumentUseCase(it)}
    }

    fun openBookmark(bookmark: Bookmark) {
        openPage(bookmark.page)
    }

    private fun openPage(page: Int) = renderer.value?.let {
        currentPage.value = it.openPage(page)
    }

    fun nextPage() = currentPage.value?.let { openPage(it.index.plus(1)) }
    fun previousPage() = currentPage.value?.let { openPage(it.index.minus(1)) }

    fun toggleBookmark() {
        val currentPage = currentPage.value?.index ?: return
        val document = document.value ?: return
        val bookmark = bookmarks.value?.firstOrNull { it.page == currentPage }

        GlobalScope.launch {
            if (bookmark == null) {
                interactors.addBookmarkUseCase(document, Bookmark(page = currentPage))
            } else {
                interactors.deleteBookmarkUseCase(document, bookmark)
            }
            bookmarks.postValue(interactors.getBookmarksUseCase(document))
        }
    }

    fun toggleInLibrary() {
        val document = document.value ?: return
        GlobalScope.launch {
            if (isInLibrary.value == true) {
                interactors.deleteDocumentUseCase(document)
            } else {
                interactors.addDocumentUseCase(document)
            }

            isInLibrary.postValue(isInLibrary(document))
        }
    }
}