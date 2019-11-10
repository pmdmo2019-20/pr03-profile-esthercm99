package es.iessaladillo.pedrojoya.profile.ui.avatar

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import es.iessaladillo.pedrojoya.profile.R
import es.iessaladillo.pedrojoya.profile.data.local.Database
import es.iessaladillo.pedrojoya.profile.data.local.DatabaseApp
import es.iessaladillo.pedrojoya.profile.data.local.entity.Avatar
import es.iessaladillo.pedrojoya.profile.data.local.entity.Contact
import es.iessaladillo.pedrojoya.profile.ui.main.ProfileActivity
import es.iessaladillo.pedrojoya.profile.ui.main.ProfileActivityViewModel
import es.iessaladillo.pedrojoya.profile.ui.main.ProfileActivityViewModelFactory
import es.iessaladillo.pedrojoya.profile.utils.toast
import kotlinx.android.synthetic.main.avatar_activity.*
import kotlinx.android.synthetic.main.profile_activity.*

class AvatarActivity : AppCompatActivity() {

    private var avatar: Avatar = DatabaseApp.queryUser().avatar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.avatar_activity)
        checkboxs()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.avatar_activity, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.mnuSelect) {
            DatabaseApp.setAvatar(avatar)
            startActivity(Intent(applicationContext, ProfileActivity::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    // CHECKBOXS:
    private fun checkboxs() {
        clickCheckbox(chkBulbasur)
        clickCheckbox(chkChikorita)
        clickCheckbox(chkCubone)
        clickCheckbox(chkFeebas)
        clickCheckbox(chkGiratina)
        clickCheckbox(chkGyarados)
        clickCheckbox(chkJigglypuff)
        clickCheckbox(chkPachirisu)
        clickCheckbox(chkPikachu)
    }
    private fun clickCheckbox(chk: CheckBox) {
        chk.setOnClickListener{oneCheck(chk)}
    }
    private fun oneCheck(chk: CheckBox) {
        // Pikachu:
        if(chkPikachu.equals(chk)) {
            chkPikachu.isChecked = true
            avatar = Database.queryAllAvatars()[0]
        } else {
            chkPikachu.isChecked = false
        }

        // Bulbasur:
        if(chkBulbasur.equals(chk)) {
            chkBulbasur.isChecked = true
            avatar = Database.queryAllAvatars()[1]
        } else {
            chkBulbasur.isChecked = false
        }

        // Chikorita:
        if(chkChikorita.equals(chk)) {
            chkChikorita.isChecked = true
            avatar = Database.queryAllAvatars()[2]
        } else {
            chkChikorita.isChecked = false
        }

        // Cubone:
        if(chkCubone.equals(chk)) {
            chkCubone.isChecked = true
            avatar = Database.queryAllAvatars()[3]
        } else {
            chkCubone.isChecked = false
        }

        // Feebas:
        if(chkFeebas.equals(chk)) {
            chkFeebas.isChecked = true
            avatar = Database.queryAllAvatars()[4]
        } else {
            chkFeebas.isChecked = false
        }

        // Girantina:
        if(chkGiratina.equals(chk)) {
            chkGiratina.isChecked = true
            avatar = Database.queryAllAvatars()[5]
        } else {
            chkGiratina.isChecked = false
        }

        // Gyarados:
        if(chkGyarados.equals(chk)) {
            chkGyarados.isChecked = true
            avatar = Database.queryAllAvatars()[6]
        } else {
            chkGyarados.isChecked = false
        }

        // Jigglypuff:
        if(chkJigglypuff.equals(chk)) {
            chkJigglypuff.isChecked = true
            avatar = Database.queryAllAvatars()[7]
        } else {
            chkJigglypuff.isChecked = false
        }

        // Pachirisu:
        if(chkPachirisu.equals(chk)) {
            chkPachirisu.isChecked = true
            avatar = Database.queryAllAvatars()[8]
        } else {
            chkPachirisu.isChecked = false
        }
    }

    companion object {
        const val EXTRA_AVATAR = "EXTRA_AVATAR"
        fun newIntent(context: Context, avatar: Bundle): Intent
                = Intent(context, AvatarActivity::class.java)
            .putExtras(bundleOf(EXTRA_AVATAR to avatar))
    }
}