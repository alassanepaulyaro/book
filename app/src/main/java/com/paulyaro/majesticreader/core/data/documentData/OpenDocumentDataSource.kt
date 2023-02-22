package com.paulyaro.majesticreader.core.data.documentData

import com.paulyaro.majesticreader.core.domain.Document

interface OpenDocumentDataSource {
    fun setOpenDocument(document: Document)
    fun getOpenDocument(): Document
}