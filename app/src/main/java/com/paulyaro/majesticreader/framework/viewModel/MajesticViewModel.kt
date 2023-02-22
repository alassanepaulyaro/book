package com.paulyaro.majesticreader.framework.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.paulyaro.majesticreader.Interactors
import com.paulyaro.majesticreader.framework.MajesticReaderApplication


open class MajesticViewModel(application: Application, protected val interactors: Interactors) : AndroidViewModel(application) {
    protected val application: MajesticReaderApplication = getApplication()
}