package com.paulyaro.majesticreader.framework.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.paulyaro.majesticreader.Interactors

object MajesticViewModelFactory: ViewModelProvider.Factory {
    lateinit var dependencies: Interactors
    lateinit var application: Application

    fun inject(application: Application, dependencies: Interactors) {
        MajesticViewModelFactory.application = application
        MajesticViewModelFactory.dependencies= dependencies
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (MajesticViewModel::class.java.isAssignableFrom(modelClass)) {
            return modelClass.getConstructor(Application::class.java, Interactors::class.java).newInstance(
                application, dependencies)
        } else {
            throw IllegalMonitorStateException("ViewModel must be extend ")
        }
    }
}