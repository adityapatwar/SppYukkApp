package com.nmproduction.sppyuk.ui.update

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.nmproduction.sppyuk.R
import com.nmproduction.sppyuk.data.constant.ApplicationConstant
import com.nmproduction.sppyuk.ui.base.BaseActivity
import com.nmproduction.sppyuk.ui.create.kelas.ClassFragment
import com.nmproduction.sppyuk.ui.create.petugas.PetugasFragment
import com.nmproduction.sppyuk.ui.create.siswa.SiswaFragment
import com.nmproduction.sppyuk.ui.create.spp.SppFragment
import com.nmproduction.sppyuk.ui.update.kelas.ClassUpdateFragment
import com.nmproduction.sppyuk.ui.update.petugas.PetugasUpdateFragment
import com.nmproduction.sppyuk.ui.update.siswa.SiswaUpdateFragment
import com.nmproduction.sppyuk.ui.update.spp.SppUpdateFragment
import kotlinx.android.synthetic.main.activity_create.*

class UpdateActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (intent.getStringExtra(ApplicationConstant.action)!!.equals("spp")) {
            supportActionBar?.title = "Edit SPP"
            addFragment(SppUpdateFragment.newInstance(intent.getStringExtra(ApplicationConstant.id)))
        } else if (intent.getStringExtra(ApplicationConstant.action)!!.equals("petugas")) {
            supportActionBar?.title = "Edit Akun Petugas"
            addFragment(PetugasUpdateFragment.newInstance(intent.getStringExtra(ApplicationConstant.id)))
        } else if (intent.getStringExtra(ApplicationConstant.action)!!.equals("kelas")) {
            supportActionBar?.title = "Edit Kelas"
            addFragment(ClassUpdateFragment.newInstance(intent.getStringExtra(ApplicationConstant.id)))
        } else if (intent.getStringExtra(ApplicationConstant.action)!!.equals("siswa")) {
            supportActionBar?.title = "Edit Akun Siswa"
            addFragment(SiswaUpdateFragment.newInstance(intent.getStringExtra(ApplicationConstant.id)))
        }

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
