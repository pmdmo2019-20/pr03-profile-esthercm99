package es.iessaladillo.pedrojoya.profile.data.local.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Avatar(var id: Int, var image: Int, var name: String) : Parcelable {

}
