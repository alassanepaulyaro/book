package com.paulyaro.majesticreader.framework.presentation.library

import android.app.Application
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.paulyaro.majesticreader.Interactors
import com.paulyaro.majesticreader.core.domain.Document
import com.paulyaro.majesticreader.framework.viewModel.MajesticViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LibraryViewModel(application: Application, interactors: Interactors) : MajesticViewModel(application, interactors) {
    val documents : MutableLiveData<List<Document>> = MutableLiveData()

    fun loadDocuments() {
        GlobalScope.launch {
            documents.postValue(interactors.getDocumentsUseCase())
        }
    }

    fun addDocument(uri: Uri) {
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                interactors.addDocumentUseCase(Document(uri.toString(), "", 0,""))
            }
            loadDocuments()
        }
    }

    fun setOpenDocument(document: Document) {
        interactors.SetOpenDocumentUseCase(document)
    }

}