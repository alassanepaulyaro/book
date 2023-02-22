package com.paulyaro.majesticreader.core.interactors.documentsUseCase

import com.paulyaro.majesticreader.core.data.documentData.DocumentRepository
import com.paulyaro.majesticreader.core.domain.Document

class RemoveDocumentUseCase (private val documentRepository: DocumentRepository) {
    suspend operator fun invoke (document: Document) =  documentRepository.removeDocument(document)
}