package com.hluck.nestednavigation.ui.navgation

const val HOME_ROUTE = "home_route"
const val LOGIN_ROUTE = "login_route"

sealed class NavRout(
    val route: String
) {
    object Home : NavRout("home")
    object Detail : NavRout("detail/{id}/{name}"){
        fun mapperRout(id:Int, name:String):String{
            return "detail/${id}/${name}"
        }
    }
    object Login : NavRout("login")
    object SignUp : NavRout("sign_up")
}