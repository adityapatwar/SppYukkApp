package com.nmproduction.sppyuk.ui.detail.history

import android.os.Bundle
import android.view.MenuItem
import com.nmproduction.sppyuk.R
import com.nmproduction.sppyuk.data.constant.ApplicationConstant
import com.nmproduction.sppyuk.ui.base.BaseActivity
import com.nmproduction.sppyuk.ui.detail.history.fragment.DetailHistoryFragment
import kotlinx.android.synthetic.main.activity_detail_history.*

class DetailHistoryActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_history)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        addFragment(DetailHistoryFragment.newInstance(intent.getStringExtra(ApplicationConstant.id)))
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
