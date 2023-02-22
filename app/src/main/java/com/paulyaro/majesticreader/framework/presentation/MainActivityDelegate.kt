package com.paulyaro.majesticreader.framework.presentation

import com.paulyaro.majesticreader.core.domain.Document

interface MainActivityDelegate {
    fun openDocument(document: Document)
}