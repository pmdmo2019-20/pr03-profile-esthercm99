package es.iessaladillo.pedrojoya.profile.data.local

import es.iessaladillo.pedrojoya.profile.R
import es.iessaladillo.pedrojoya.profile.data.local.entity.Avatar

object Database {

    private val avatars = listOf(
        Avatar(1, R.drawable.pikachu, "Pikachu"),
        Avatar(2, R.drawable.bulbasur, "Bulbasur"),
        Avatar(3, R.drawable.chikorita, "Chikorita"),
        Avatar(4, R.drawable.cubone, "Cubone"),
        Avatar(5, R.drawable.feebas, "Feebas"),
        Avatar(6, R.drawable.giratina, "Giratina"),
        Avatar(7, R.drawable.gyarados, "Gyarados"),
        Avatar(8, R.drawable.jigglypuff, "Jigglypuff"),
        Avatar(9, R.drawable.pachirisu, "Pachirisu")
    )

    fun queryDefaultAvatar(): Avatar = avatars[0]
    fun queryAllAvatars(): List<Avatar> = ArrayList(avatars)
    fun getAvatar(draw: Int): Avatar {
        var avatar : Avatar = avatars[0]

        if (draw == avatars[0].image) {
            avatar = avatars[0]
        } else if (draw == avatars[1].image) {
            avatar = avatars[1]
        } else if (draw == avatars[2].image) {
            avatar = avatars[2]
        } else if (draw == avatars[3].image) {
            avatar = avatars[3]
        } else if (draw == avatars[4].image) {
            avatar = avatars[4]
        } else if (draw == avatars[5].image) {
            avatar = avatars[5]
        } else if (draw == avatars[6].image) {
            avatar = avatars[6]
        } else if (draw == avatars[7].image) {
            avatar = avatars[7]
        } else if (draw == avatars[8].image) {
            avatar = avatars[8]
        }

        return avatar
    }

}
