package es.iessaladillo.pedrojoya.profile.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import es.iessaladillo.pedrojoya.profile.R
import es.iessaladillo.pedrojoya.profile.data.local.Database
import es.iessaladillo.pedrojoya.profile.data.local.DatabaseApp
import es.iessaladillo.pedrojoya.profile.data.local.entity.Avatar
import es.iessaladillo.pedrojoya.profile.data.local.entity.Contact
import es.iessaladillo.pedrojoya.profile.ui.avatar.AvatarActivity
import es.iessaladillo.pedrojoya.profile.utils.hideSoftKeyboard
import es.iessaladillo.pedrojoya.profile.utils.toast
import kotlinx.android.synthetic.main.profile_activity.*

//import kotlinx.android.synthetic.main.profile_avatar.*
//import kotlinx.android.synthetic.main.profile_form.*

const val RC_AVATAR_SELECTION = 1

class ProfileActivity : AppCompatActivity() {

    private lateinit var viewModel: ProfileActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_activity)

        val factory = ProfileActivityViewModelFactory(this.application)
        viewModel = ViewModelProvider(this, factory).get(ProfileActivityViewModel::class.java)

        showDate()
        setupViews()
    }

    private fun showDate() {
        avatar.setImageDrawable(getDrawable(viewModel.user.avatar.image))
        lblAvatar.text = viewModel.user.avatar.name
        txtName.setText(viewModel.user.name)
        txtEmail.setText(viewModel.user.email)
        txtPhone.setText(viewModel.user.phone)
        txtAddress.setText(viewModel.user.address)
        txtWeb.setText(viewModel.user.web)
    }
    private fun setupViews() {
        callBtnIcons()
        callAvatarActivity()
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
                viewModel.showToast(getString(R.string.SavedSuccessfully))
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun save() {
        DatabaseApp.setAvatar(Database.getAvatar(viewModel.avatarImg.image))
        DatabaseApp.setNombre(txtName.text.toString())
        DatabaseApp.setEmail(txtEmail.text.toString())
        DatabaseApp.setPhone(txtPhone.text.toString())
        DatabaseApp.setAddress(txtAddress.text.toString())
        DatabaseApp.setWeb(txtWeb.text.toString())
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

    private fun callAvatarActivity() {
        imageAvatar.setOnClickListener{navigateToAvatarActivity()}
        view.hideSoftKeyboard()
    }
    private fun navigateToAvatarActivity() {
        save()
        val intent = AvatarActivity.newIntent(applicationContext, viewModel.obtainAvatarBundle())
        startActivityForResult(intent, RC_AVATAR_SELECTION)
    }
}
