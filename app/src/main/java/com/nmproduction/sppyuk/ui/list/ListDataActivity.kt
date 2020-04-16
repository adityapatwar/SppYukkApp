package com.nmproduction.sppyuk.ui.list

import android.os.Bundle
import android.view.MenuItem
import com.nmproduction.sppyuk.R
import com.nmproduction.sppyuk.ui.base.BaseActivity
import com.nmproduction.sppyuk.ui.list.fragment.ListDataFragment
import kotlinx.android.synthetic.main.activity_list_data.*

class ListDataActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_data)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        addFragment(ListDataFragment())

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

}
