package es.iessaladillo.pedrojoya.profile.ui.main

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ProfileActivityViewModelFactory(val application: Application, val name: String, val email: String, val phone: String, val address: String, val web: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileActivityViewModel(application, name, email, phone, address, web) as T
    }
}