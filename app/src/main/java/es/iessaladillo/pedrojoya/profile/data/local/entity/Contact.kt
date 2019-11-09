package es.iessaladillo.pedrojoya.profile.data.local.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Contact(var avatar: Avatar, var name: String, var email: String, var phone: String, var address: String, var web: String) : Parcelable