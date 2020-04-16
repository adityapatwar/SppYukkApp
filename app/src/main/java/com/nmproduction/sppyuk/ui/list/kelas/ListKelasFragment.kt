package com.nmproduction.sppyuk.ui.list.kelas

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

import com.nmproduction.sppyuk.R
import com.nmproduction.sppyuk.data.constant.ApplicationConstant
import com.nmproduction.sppyuk.data.model.kelas.KelasModel
import com.nmproduction.sppyuk.ui.base.BaseFragment
import com.nmproduction.sppyuk.ui.list.ListDataActivity
import com.nmproduction.sppyuk.ui.list.adapter.ItemDecoration
import com.nmproduction.sppyuk.ui.list.kelas.adapter.KelasListAdapter
import com.nmproduction.sppyuk.ui.list.siswa.ListSiswaInterface
import com.nmproduction.sppyuk.ui.menu.MenuActivity
import com.nmproduction.sppyuk.ui.update.UpdateActivity
import com.nmproduction.sppyuk.utils.MainUtilities
import kotlinx.android.synthetic.main.fragment_list_kelas.*

/**
 * A simple [Fragment] subclass.
 */
class ListKelasFragment : BaseFragment(), ListKelasInterface.View {

    private lateinit var presenter: ListKelasPresenter
    private lateinit var myAdapter: KelasListAdapter
    private var data: MutableList<KelasModel?> = mutableListOf()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_kelas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = ListKelasPresenter(
            this,
            FirebaseDatabase.getInstance().getReference(ApplicationConstant.kelas)
        )

        myAdapter = KelasListAdapter(data) { data: KelasModel ->
            choose(data)
        }

        presenter.getData()

    }

    private fun choose(data: KelasModel) {
        val builder = AlertDialog.Builder(requireContext())
        val items = arrayOf("Update Data", " Delete Data")
        builder.setTitle("Selection Action")
        builder.setItems(items, DialogInterface.OnClickListener { dialog, which ->
            if (which == 0) {
                val intent = Intent(activity, UpdateActivity::class.java)
                intent.putExtra(ApplicationConstant.action, "kelas")
                intent.putExtra(ApplicationConstant.id, data.id)
                startActivity(intent)
            } else if (which == 1) {
            presenter.removeData(data)
            }
        })

        builder.show()


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rvKelas?.layoutManager = LinearLayoutManager(requireContext())
        rvKelas?.adapter = myAdapter
        rvKelas?.addItemDecoration(
            ItemDecoration(
                MainUtilities.intToDp(
                    8,
                    resources
                )
            )
        )

    }

    override fun showWarning(message: String) {
        showSnackBar(message, view!!)
    }

    override fun showData(kelasModel: List<KelasModel?>?) {
        data.clear()
        kelasModel?.let { data.addAll(it) }
        myAdapter.notifyDataSetChanged()
    }

    override fun messageDelete(message: String) {
        presenter.getData()
        showSnackBar(message, view!!)
    }


}
