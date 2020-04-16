package com.nmproduction.sppyuk.ui.list.petugas

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.FirebaseDatabase

import com.nmproduction.sppyuk.R
import com.nmproduction.sppyuk.data.constant.ApplicationConstant
import com.nmproduction.sppyuk.data.model.kelas.KelasModel
import com.nmproduction.sppyuk.data.model.petugas.PetugasModel
import com.nmproduction.sppyuk.ui.base.BaseFragment
import com.nmproduction.sppyuk.ui.list.adapter.ItemDecoration
import com.nmproduction.sppyuk.ui.list.petugas.adapter.PetugasListAdapter
import com.nmproduction.sppyuk.ui.update.UpdateActivity
import com.nmproduction.sppyuk.utils.MainUtilities
import kotlinx.android.synthetic.main.fragment_list_petugas.*

/**
 * A simple [Fragment] subclass.
 */
class ListPetugasFragment : BaseFragment(), ListPetugasInterface.View {


    private lateinit var presenter: ListPetugasPresenter
    private lateinit var myAdapter: PetugasListAdapter
    private var data: MutableList<PetugasModel?> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_petugas, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = ListPetugasPresenter(
            this,
            FirebaseDatabase.getInstance().getReference(ApplicationConstant.petugas)
        )

        myAdapter = PetugasListAdapter(data) { data: PetugasModel ->
            choose(data)
        }

        presenter.getData()

    }

    private fun choose(data: PetugasModel) {
        val builder = AlertDialog.Builder(requireContext())
        val items = arrayOf("Update Data", " Delete Data")
        builder.setTitle("Selection Action")
        builder.setItems(items, DialogInterface.OnClickListener { dialog, which ->
            if (which == 0) {
                val intent = Intent(activity, UpdateActivity::class.java)
                intent.putExtra(ApplicationConstant.action, "petugas")
                intent.putExtra(ApplicationConstant.id, data.id)
                startActivity(intent)
            } else if (which == 1) {
                FirebaseDatabase.getInstance().getReference(ApplicationConstant.kelas)
                    .child(data.id).removeValue()
            }
        })

        builder.show()


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rvPetugas?.layoutManager = LinearLayoutManager(requireContext())
        rvPetugas?.adapter = myAdapter
        rvPetugas?.addItemDecoration(
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

    override fun showData(kelasModel: List<PetugasModel?>?) {
        data.clear()
        kelasModel?.let { data.addAll(it) }
        myAdapter.notifyDataSetChanged()
    }

    override fun messageDelete(message: String) {
        presenter.getData()
        showSnackBar(message, view!!)
    }


}
