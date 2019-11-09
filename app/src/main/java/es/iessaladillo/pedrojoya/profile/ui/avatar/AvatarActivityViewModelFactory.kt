package es.iessaladillo.pedrojoya.profile.ui.avatar

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AvatarActivityViewModelFactory (val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AvatarActivityViewModel(application) as T
    }
}