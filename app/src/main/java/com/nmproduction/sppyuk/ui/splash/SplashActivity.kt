package com.nmproduction.sppyuk.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.nmproduction.sppyuk.R
import com.nmproduction.sppyuk.data.preference.AlphaPreferences
import com.nmproduction.sppyuk.ui.base.BaseActivity
import com.nmproduction.sppyuk.ui.login.LoginActivity

class SplashActivity : BaseActivity() ,SplashInterface.View{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            val preferences = AlphaPreferences(applicationContext)
            startActivity<LoginActivity>(applicationContext)
            finish()

        }, 2000)
    }

    override fun showWarning(message: String) {

    }
}
