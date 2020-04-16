package com.nmproduction.sppyuk.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nmproduction.sppyuk.R
import com.nmproduction.sppyuk.ui.base.BaseActivity
import com.nmproduction.sppyuk.ui.login.fragment.LoginFragment

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        addFragment(LoginFragment())
    }
}
