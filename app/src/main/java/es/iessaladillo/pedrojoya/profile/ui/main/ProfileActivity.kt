package es.iessaladillo.pedrojoya.profile.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import es.iessaladillo.pedrojoya.profile.R
import es.iessaladillo.pedrojoya.profile.utils.hideSoftKeyboard
//import es.iessaladillo.pedrojoya.profile.data.local.entity.Avatar
import kotlinx.android.synthetic.main.profile_activity.*
//import kotlinx.android.synthetic.main.profile_avatar.*
//import kotlinx.android.synthetic.main.profile_form.*

class ProfileActivity : AppCompatActivity() {
    private lateinit var viewModel: ProfileActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_activity)
        setupViews()
    }

    private fun setupViews() {
        createView()
        callBtnIcons()
    }

    private fun createView() {
        val factory = ProfileActivityViewModelFactory(  this.application,
                                                        txtName.text.toString(),
                                                        txtEmail.text.toString(),
                                                        txtPhone.text.toString(),
                                                        txtAddress.text.toString(),
                                                        txtWeb.text.toString())
        viewModel = ViewModelProvider(this, factory).get(ProfileActivityViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.profile_activity, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        view.hideSoftKeyboard()
        if (item.itemId == R.id.mnuSave) {
            if(isAllInformationCompleted() && isAllInformationValid()) {
                save()
                viewModel.showToast("User saved successfully")
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun save() {
        viewModel.contact.name = txtName.text.toString()
        viewModel.contact.email = txtEmail.text.toString()
        viewModel.contact.phone = txtPhone.text.toString()
        viewModel.contact.address = txtAddress.text.toString()
        viewModel.contact.web = txtWeb.text.toString()
    }
    private fun isAllInformationCompleted(): Boolean {
        return  !viewModel.isEmptyInformation(txtName)  &&  !viewModel.isEmptyInformation(txtEmail) &&
                !viewModel.isEmptyInformation(txtPhone) &&  !viewModel.isEmptyInformation(txtAddress) &&
                !viewModel.isEmptyInformation(txtWeb)
    }
    private fun isAllInformationValid(): Boolean {
        return viewModel.isValidEmail(txtEmail) && viewModel.isValidPhone(txtPhone) && viewModel.isValidUrl(txtWeb)
    }
    private fun callBtnIcons() {
        viewModel.clickbtnEmail(iconEmail, txtEmail)
        viewModel.clickbtnPhone(iconPhone, txtPhone)
        viewModel.clickbtnAddress(iconAddress, txtAddress)
        viewModel.clickbtnUrl(iconWeb, txtWeb)
        view.hideSoftKeyboard()
    }
}
