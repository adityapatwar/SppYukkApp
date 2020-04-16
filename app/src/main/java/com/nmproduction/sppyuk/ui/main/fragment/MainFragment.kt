package com.nmproduction.sppyuk.ui.main.fragment

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth

import com.nmproduction.sppyuk.R
import com.nmproduction.sppyuk.data.preference.AlphaPreferences
import com.nmproduction.sppyuk.ui.base.BaseFragment
import com.nmproduction.sppyuk.ui.create.CreateActivity
import com.nmproduction.sppyuk.ui.history.HistoryActivity
import com.nmproduction.sppyuk.ui.list.ListDataActivity
import com.nmproduction.sppyuk.ui.login.LoginActivity
import com.nmproduction.sppyuk.ui.menu.MenuActivity
import com.nmproduction.sppyuk.ui.pembayaran.PembayaranActivity
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : BaseFragment(), MainInterface.View {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val alphaPreferences = AlphaPreferences(requireContext())
        if(alphaPreferences.level.toString().equals("3")){
            btnData.setOnClickListener {
                choose()
            }

            btnGenerate.setOnClickListener {
                startActivity<HistoryActivity>(requireContext())
            }

            btnTransaksi.setOnClickListener {
                startActivity<PembayaranActivity>(requireContext())
            }

            btnHistory.setOnClickListener {
                startActivity<HistoryActivity>(requireContext())
            }
        }else if(alphaPreferences.level.toString().equals("2")  ){
            if(alphaPreferences.level.toString().equals("2")) {
                frame1.visibility = View.VISIBLE
                frame4.visibility = View.VISIBLE
            }
            btnTransaksi.setOnClickListener {
                startActivity<PembayaranActivity>(requireContext())
            }

            btnHistory.setOnClickListener {
                startActivity<HistoryActivity>(requireContext())
            }
        }else{
            btnHistory.setOnClickListener {
                startActivity<HistoryActivity>(requireContext())

            }

            frame1.visibility = View.VISIBLE
            frame2.visibility = View.VISIBLE
            frame4.visibility = View.VISIBLE
        }


        logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()

            alphaPreferences.clearAll()
            startActivity<LoginActivity>(requireContext())
            activity?.finish()

        }






    }

    private fun choose() {
        val builder = AlertDialog.Builder(requireContext())
        val items = arrayOf("Create Data", "Update or Delete Data")
        builder.setTitle("Selection Action")
        builder.setItems(items, DialogInterface.OnClickListener { dialog, which ->
            if (which == 0) {
                startActivity<MenuActivity>(requireContext())
            } else if (which == 1) {
                startActivity<ListDataActivity>(requireContext())
            }
        })

        builder.show()


    }

    override fun showWarning(message: String) {

    }

}
