package es.iessaladillo.pedrojoya.profile.ui.main

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ProfileActivityViewModelFactory(val application: Application, val state: Bundle?) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileActivityViewModel(application, state) as T
    }
}