package com.nmproduction.sppyuk.ui.login.fragment


interface LoginInterface {
    interface View {
        fun showWarning(message: String)
        fun loginSuccess(uid: String, message: String)
    }

    interface Presenter {
        fun login(email: String, password: String,level : String)

    }
}