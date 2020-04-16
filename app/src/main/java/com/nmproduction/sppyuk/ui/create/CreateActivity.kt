package com.nmproduction.sppyuk.ui.create

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.nmproduction.sppyuk.R
import com.nmproduction.sppyuk.data.constant.ApplicationConstant
import com.nmproduction.sppyuk.ui.base.BaseActivity
import com.nmproduction.sppyuk.ui.create.kelas.ClassFragment
import com.nmproduction.sppyuk.ui.create.petugas.PetugasFragment
import com.nmproduction.sppyuk.ui.create.siswa.SiswaFragment
import com.nmproduction.sppyuk.ui.create.spp.SppFragment
import kotlinx.android.synthetic.main.activity_create.*

class CreateActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (intent.getStringExtra(ApplicationConstant.action)!!.equals("spp")) {
            supportActionBar?.title = "SPP"
            addFragment(SppFragment())
        } else if (intent.getStringExtra(ApplicationConstant.action)!!.equals("petugas")) {
            supportActionBar?.title = "Buat Akun Petugas"
            addFragment(PetugasFragment())
        } else if (intent.getStringExtra(ApplicationConstant.action)!!.equals("kelas")) {
            supportActionBar?.title = "Kelas"
            addFragment(ClassFragment())
        } else if (intent.getStringExtra(ApplicationConstant.action)!!.equals("siswa")) {
            supportActionBar?.title = "Buat Akun Siswa"
            addFragment(SiswaFragment())
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
