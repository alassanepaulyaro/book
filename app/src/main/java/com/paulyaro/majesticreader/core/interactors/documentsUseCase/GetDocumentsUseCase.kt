package com.paulyaro.majesticreader.core.interactors.documentsUseCase

import com.paulyaro.majesticreader.core.data.documentData.DocumentRepository

class GetDocumentsUseCase (private val documentRepository: DocumentRepository) {
    suspend operator fun invoke () = documentRepository.getDocuments()
}