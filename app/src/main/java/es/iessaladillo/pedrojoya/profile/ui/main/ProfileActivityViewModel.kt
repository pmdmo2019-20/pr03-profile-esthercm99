package es.iessaladillo.pedrojoya.profile.ui.main
import android.app.Application
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.profile.data.local.user.Contact
import es.iessaladillo.pedrojoya.profile.utils.*


class ProfileActivityViewModel(application: Application, name: String, email: String, phone: String, address: String, web: String) : ViewModel() {
    var contact: Contact = Contact(name, email, phone, address, web)
    val app = application

    fun isEmptyInformation(text: EditText): Boolean {
        var isEmpty = false
        if(text.text.toString().isEmpty()) {
            isEmpty = true
            text.error = "Empty text"
        }
        return isEmpty
    }
    fun isValidPhone(text: EditText): Boolean {
        if(!text.text.toString().isValidPhone()) {
            text.error = "Invalid phone"
            return false
        } else {
            return true
        }
    }
    fun isValidEmail(text: EditText): Boolean {
        if(!text.text.toString().isValidEmail()) {
            text.error = "Invalid email"
            return false
        } else {
            return true
        }
    }
    fun isValidUrl(text: EditText): Boolean {
        if(!text.text.toString().isValidUrl()) {
            text.error = "Invalid url"
            return false
        } else {
            return true
        }
    }

    fun clickbtnUrl(btn: ImageButton, text: EditText) {
        btn.setOnClickListener{verifyIconUrl(text)}
    }
    fun clickbtnPhone(btn: ImageButton, text: EditText) {
        btn.setOnClickListener{verifyIconPhone(text)}
    }
    fun clickbtnAddress(btn: ImageButton, text: EditText) {
        btn.setOnClickListener{verifyIconAddress(text)}
    }
    fun clickbtnEmail(btn: ImageButton, text: EditText) {
        btn.setOnClickListener{verifyIconEmail(text)}
    }

    private fun verifyIconUrl(text: EditText) {
        if (isValidUrl(text) && !isEmptyInformation(text)) {
            if(isActivityAvailable(app.applicationContext, newViewUriIntent(Uri.parse(text.text.toString())))){
                app.applicationContext.startActivity(Intent.createChooser(newViewUriIntent(Uri.parse(text.text.toString())), "Navegar ").addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
            } else {
                showToast("There is no application to perform the action")
            }
        }
    }
    private fun verifyIconPhone(text: EditText) {
        if(isValidPhone(text) && !isEmptyInformation(text)) {
            if(isActivityAvailable(app.applicationContext, newDialIntent(text.text.toString()))) {
                app.applicationContext.startActivity(Intent.createChooser(newDialIntent(text.text.toString()), "Llamar a ").addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
            } else {
                showToast("There is no application to perform the action")
            }
        }
    }
    private fun verifyIconAddress(text: EditText) {
        if(!isEmptyInformation(text)) {
            if(isActivityAvailable(app.applicationContext, newSearchInMapIntent(text.text.toString()))) {
                app.applicationContext.startActivity(Intent.createChooser(newSearchInMapIntent(text.text.toString()), "Ir al mapa de  ").addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
            } else {
                showToast("There is no application to perform the action")
            }
        }
    }
    private fun verifyIconEmail(text: EditText) {
        if(isValidEmail(text) && !isEmptyInformation(text)) {
            if(isActivityAvailable(app.applicationContext, newEmailIntent(text.text.toString()))) {
                app.applicationContext.startActivity(Intent.createChooser(newEmailIntent(text.text.toString()), "Email ").addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
            } else {
                showToast("There is no application to perform the action")
            }
        }
    }

    fun showToast(message: String) {
        app.applicationContext.toast(message)
    }
}