package com.paulyaro.majesticreader.core.data.documentData

import com.paulyaro.majesticreader.core.domain.Document

class DocumentRepository (private val documentDataSource: DocumentDataSource, private val openDocumentDataSource: OpenDocumentDataSource) {
    suspend fun addDocument(document: Document) = documentDataSource.add(document)
    suspend fun getDocuments() = documentDataSource.readAll()
    suspend fun removeDocument(document: Document) =  documentDataSource.remove(document)
    fun setOpenDocument(document: Document) = openDocumentDataSource.setOpenDocument(document)
    fun getOpenDocument():Document = openDocumentDataSource.getOpenDocument()
}