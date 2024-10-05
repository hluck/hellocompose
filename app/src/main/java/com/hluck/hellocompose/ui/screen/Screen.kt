package com.hluck.hellocompose.ui.screen


const val DETAIL_ARGUMENT_ID = "id"
const val DETAIL_ARGUMENT_NAME = "name"

const val AUTHENTICATION_ROUTE = "authentication"
const val HOME_ROUTE = "home"
const val ROOT_ROUTE = "root"

sealed class Screen(
    val route: String
) {
    object Home : Screen("home_screen")
    //路径参数用法
//    object Detail : Screen("detail_screen/{$DETAIL_ARGUMENT_ID}/{$DETAIL_ARGUMENT_NAME}") {
//        fun passIdAndName(id: Int, name: String): String {
////            return "detail_screen/$id"
//            return this.route.replace(
//                oldValue = "{$DETAIL_ARGUMENT_ID}/{$DETAIL_ARGUMENT_NAME}",
//                newValue = "${id}/$name"
//            )
//        }
//    }

    //请求参数用法
    object Detail:Screen(
        "detail_screen?id={$DETAIL_ARGUMENT_ID}&name={$DETAIL_ARGUMENT_NAME}"
    ){
        fun passIdAndName(id:Int,name:String):String{
            return "detail_screen?id=$id&name=$name"
        }
    }

    object Login : Screen("login_screen")
    object SignUp : Screen("signup_screen")
}