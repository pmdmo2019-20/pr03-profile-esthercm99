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
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import es.iessaladillo.pedrojoya.profile.R
import es.iessaladillo.pedrojoya.profile.data.local.Database
import es.iessaladillo.pedrojoya.profile.data.local.entity.Avatar
import kotlinx.android.synthetic.main.avatar_activity.*
import kotlinx.android.synthetic.main.profile_activity.*

class AvatarActivity : AppCompatActivity() {

    lateinit var avatarImg: Avatar
    private lateinit var viewModel: AvatarActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.avatar_activity)
        createView()
        getIntentData()
    }

    private fun getIntentData() {
        if (intent == null || !intent.hasExtra(EXTRA_AVATAR)) {
            throw RuntimeException(
                "DateSelectionActivity needs to receive day, month and year as extras")
        }
        avatarImg = intent.getParcelableExtra(EXTRA_AVATAR)
    }


    private fun createView() {
        val factory = AvatarActivityViewModelFactory(this.application)
        viewModel = ViewModelProvider(this, factory).get(AvatarActivityViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.avatar_activity, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.mnuSelect) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_AVATAR = "EXTRA_AVATAR"
        fun newIntent(context: Context, avatar: Bundle): Intent
                = Intent(context, AvatarActivity::class.java)
            .putExtras(bundleOf(EXTRA_AVATAR to avatar))
    }
}



