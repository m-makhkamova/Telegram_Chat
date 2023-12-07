package uz.itschool.telegram_chat

class UserData(
    var name:String? = null,
    var uid:String? = null,
    var email:String? = null,
    var photo:String? = null
) {

    constructor(): this(null,null,null,null)
}