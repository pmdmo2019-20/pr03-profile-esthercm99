package es.iessaladillo.pedrojoya.profile.ui.main

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import es.iessaladillo.pedrojoya.profile.R
import es.iessaladillo.pedrojoya.profile.data.local.Database
import es.iessaladillo.pedrojoya.profile.data.local.entity.Avatar
import es.iessaladillo.pedrojoya.profile.ui.avatar.AvatarActivity
import es.iessaladillo.pedrojoya.profile.utils.hideSoftKeyboard
import kotlinx.android.synthetic.main.profile_activity.*
//import kotlinx.android.synthetic.main.profile_avatar.*
//import kotlinx.android.synthetic.main.profile_form.*

const val RC_AVATAR_SELECTION = 2

class ProfileActivity : AppCompatActivity() {
    private var avatarImg: Avatar = Database.queryDefaultAvatar()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_activity)
        setupViews()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if (resultCode == RESULT_OK && requestCode == RC_AVATAR_SELECTION && intent != null) {
            extractResult(intent)
            showDate()
        }
    }

    private fun extractResult(intent: Intent) {
        if (!intent.hasExtra(AvatarActivity.EXTRA_AVATAR)) {
            throw RuntimeException(
                "AvatarActivity must receive an avatar in result intent")
        }
        avatarImg = intent.getParcelableExtra(AvatarActivity.EXTRA_AVATAR)
    }

    private fun showDate() {
        viewModel.showToast(String.format("Avatar: %s", avatarImg.getName()))
    }

    private fun setupViews() {
        createView()
        callBtnIcons()
        callAvatar()
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
    private fun callAvatar() {
        imageAvatar.setOnClickListener{navigateToAvatarActivity()}
        view.hideSoftKeyboard()
    }

    private fun navigateToAvatarActivity() {
        val intent = AvatarActivity.newIntent(applicationContext, viewModel.obtainAvatarBundle())
        startActivityForResult(intent, RC_AVATAR_SELECTION)
    }
}
