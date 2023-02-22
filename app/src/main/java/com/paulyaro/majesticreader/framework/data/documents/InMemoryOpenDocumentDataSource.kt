package com.paulyaro.majesticreader.framework.data.documents

import com.paulyaro.majesticreader.core.data.documentData.OpenDocumentDataSource
import com.paulyaro.majesticreader.core.domain.Document

class InMemoryOpenDocumentDataSource :OpenDocumentDataSource {

    private var openDocument = Document.EMPTY

    override fun setOpenDocument(document: Document) {
        openDocument = document
    }

    override fun getOpenDocument(): Document = openDocument
}