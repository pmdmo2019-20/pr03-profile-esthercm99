package es.iessaladillo.pedrojoya.profile.data.local.user

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Contact(var name: String, var email: String, var phone: String, var address: String, var web: String) : Parcelable