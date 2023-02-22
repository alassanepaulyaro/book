package com.paulyaro.majesticreader.framework.data.documents

import android.content.Context
import com.paulyaro.majesticreader.core.data.documentData.DocumentDataSource
import com.paulyaro.majesticreader.core.domain.Document
import com.paulyaro.majesticreader.framework.data.MajesticReaderDatabase
import com.paulyaro.majesticreader.framework.utils.FileUtil

class RoomDocumentDataSource (val context: Context) :DocumentDataSource {
    private val documentDao = MajesticReaderDatabase.getInstance(context).documentDao()
    override suspend fun add(document: Document) {
        val details = FileUtil.getDocumentDetails(context, document.url)
        documentDao.addDocument(DocumentEntity(document.url, details.name, details.size, details.thumnail))
    }

    override suspend fun readAll(): List<Document> =
        documentDao.getDocument().map { Document(it.uri, it.title, it.size, it.thumbnailUri) }

    override suspend fun remove(document: Document) =
        documentDao.removeDocument(DocumentEntity(document.url,  document.name, document.size, document.thumbnail))

}