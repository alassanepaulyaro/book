package com.paulyaro.majesticreader.core.interactors.documentsUseCase

import com.paulyaro.majesticreader.core.data.documentData.DocumentRepository
import com.paulyaro.majesticreader.core.domain.Document

class SetOpenDocumentUseCase (private val documentRepository: DocumentRepository) {
    operator fun invoke(document: Document) = documentRepository.setOpenDocument(document)
}