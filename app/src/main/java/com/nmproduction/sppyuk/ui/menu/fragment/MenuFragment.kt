package com.nmproduction.sppyuk.ui.menu.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.nmproduction.sppyuk.R
import com.nmproduction.sppyuk.data.constant.ApplicationConstant
import com.nmproduction.sppyuk.ui.base.BaseFragment
import com.nmproduction.sppyuk.ui.create.CreateActivity
import kotlinx.android.synthetic.main.fragment_menu.*

/**
 * A simple [Fragment] subclass.
 */
class MenuFragment : BaseFragment(), MenuInterface.View {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        actionSpp.setOnClickListener {
            val intent = Intent(activity, CreateActivity::class.java)
            intent.putExtra(ApplicationConstant.action, "spp")
            startActivity(intent)
        }
        actionKelas.setOnClickListener {
            val intent = Intent(activity, CreateActivity::class.java)
            intent.putExtra(ApplicationConstant.action, "kelas")
            startActivity(intent)
        }
        actionPetugas.setOnClickListener {
            val intent = Intent(activity, CreateActivity::class.java)
            intent.putExtra(ApplicationConstant.action, "petugas")
            startActivity(intent)
        }
        actionSiswa.setOnClickListener {
            val intent = Intent(activity, CreateActivity::class.java)
            intent.putExtra(ApplicationConstant.action, "siswa")
            startActivity(intent)
        }

    }

    override fun showWarning(message: String) {

    }

}
