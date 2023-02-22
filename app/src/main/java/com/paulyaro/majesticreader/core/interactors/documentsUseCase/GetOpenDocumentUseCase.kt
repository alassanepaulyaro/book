package com.paulyaro.majesticreader.core.interactors.documentsUseCase

import com.paulyaro.majesticreader.core.data.documentData.DocumentRepository

class GetOpenDocumentUseCase (private val documentRepository: DocumentRepository) {
    operator fun invoke() = documentRepository.getOpenDocument()
}