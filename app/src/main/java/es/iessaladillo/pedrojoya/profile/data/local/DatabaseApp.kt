package es.iessaladillo.pedrojoya.profile.data.local

import es.iessaladillo.pedrojoya.profile.data.local.entity.Avatar
import es.iessaladillo.pedrojoya.profile.data.local.entity.Contact

object DatabaseApp {

    private val user = Contact(Database.queryDefaultAvatar(), "", "", "", "", "")

    fun setAvatar(avatarImg: Avatar) {
        user.avatar = avatarImg
    }
    fun setNombre(name: String) {
        user.name = name
    }
    fun setEmail(email: String) {
        user.email = email
    }
    fun setPhone(phone: String) {
        user.phone = phone
    }
    fun setAddress(address: String) {
        user.address = address
    }
    fun setWeb(web: String) {
        user.web = web
    }

    fun queryUser(): Contact = user

}
