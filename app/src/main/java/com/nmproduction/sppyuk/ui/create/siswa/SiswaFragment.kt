package com.nmproduction.sppyuk.ui.create.siswa

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.nmproduction.sppyuk.R
import com.nmproduction.sppyuk.data.constant.ApplicationConstant
import com.nmproduction.sppyuk.data.model.kelas.DataKelasTmp
import com.nmproduction.sppyuk.data.model.kelas.KelasModel
import com.nmproduction.sppyuk.data.model.siswa.DataKelas
import com.nmproduction.sppyuk.data.model.siswa.DataSPP
import com.nmproduction.sppyuk.data.model.siswa.SiswaModel
import com.nmproduction.sppyuk.data.model.spp.utama.DataSppTemp
import com.nmproduction.sppyuk.data.model.spp.utama.SppUtamaModel
import com.nmproduction.sppyuk.ui.base.BaseFragment
import com.nmproduction.sppyuk.ui.create.siswa.adapter.KelasAdapter
import com.nmproduction.sppyuk.ui.create.siswa.adapter.SppAdapter
import com.nmproduction.sppyuk.ui.loading.LoadingDialogFragment
import com.nmproduction.sppyuk.utils.MainUtilities
import kotlinx.android.synthetic.main.fragment_siswa.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class SiswaFragment : BaseFragment(), SiswaInterface.View {

    lateinit var presenter: SiswaPresenter
    var listKelas: MutableList<KelasModel> = ArrayList()
    var listSpp: MutableList<SppUtamaModel> = ArrayList()
    private val loading = LoadingDialogFragment()


    var tmplistKelas: MutableList<DataKelasTmp> = ArrayList()
    var tmplistSpp: MutableList<DataSppTemp> = ArrayList()


    private lateinit var adapterSpp: SppAdapter
    private lateinit var adapterKelas: KelasAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_siswa, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = SiswaPresenter(
            this,
            FirebaseDatabase.getInstance().getReference(ApplicationConstant.siswa)
        )

        adapterSpp =
            SppAdapter(view.context, R.layout.spinner_dropdown_item, tmplistSpp)
        adapterKelas =
            KelasAdapter(view.context, R.layout.spinner_dropdown_item, tmplistKelas)

        spSpp.adapter = adapterSpp
        spKelas.adapter = adapterKelas

        showGrade()
        showSpp()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        create.setOnClickListener {
            val spp: DataSppTemp = spSpp.selectedItem as DataSppTemp
            val kelas: DataKelasTmp = spKelas.selectedItem as DataKelasTmp

            val year = SimpleDateFormat("yyyy")
            val yearcount = year.format(Date()).toInt() + spp.masaBerlaku.toInt()
            val sdf = SimpleDateFormat("dd/M")
            val currentDate = sdf.format(Date())


            if (username.text.toString().equals("")) {
                showSnackBar("Username Kosong", view!!)
            } else if (password.text.toString().equals("")) {
                showSnackBar("Password Kosong", view!!)
            } else if (name.text.toString().equals("")) {
                showSnackBar("Nama Kosong", view!!)
            } else if (nisn.text.toString().equals("")) {
                showSnackBar("Nisn Kosong", view!!)
            } else if (nis.text.toString().equals("")) {
                showSnackBar("Nis Kosong", view!!)
            } else if (kelas.nama.equals("pilih kelas", true)) {
                showSnackBar("Pilih Kelas !", view!!)
            } else if (alamat.text.toString().equals("")) {
                showSnackBar("Alamat Kosong", view!!)
            } else if (noTel.text.toString().equals("")) {
                showSnackBar("No Phone Kosong", view!!)
            } else if (spp.nama.equals("pilih spp", true)) {
                showSnackBar("pilih spp !", view!!)
            } else {
                val dataKelas =
                    DataKelas(kelas.tingkat, kelas.nama.toString(), kelas.KompetesiKeahlian)
                val dataSpp = DataSPP(spp.nominal, "$currentDate/$yearcount")
                val siswaModel = SiswaModel(
                    "",
                    username.text.toString(),
                    password.text.toString(),
                    name.text.toString(),
                    nisn.text.toString(),
                    nis.text.toString(),
                    dataKelas,
                    alamat.text.toString(),
                    noTel.text.toString(),
                    dataSpp
                )
                presenter.createSiswa(siswaModel)
            }
        }

    }

    override fun showWarning(message: String) {
        showSnackBar(message, view!!)
    }

    override fun createSuccess(message: String) {
        showSnackBar(message, view!!)
    }

    override fun showSpp() {
        val sppRef = FirebaseDatabase.getInstance().getReference(ApplicationConstant.spp)
        listSpp.clear()
        tmplistSpp.clear()
        tmplistSpp.add(DataSppTemp(0, "Pilih SPP", "", "", ""))
        val sppListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (h in dataSnapshot.children) {
                    val data = h.getValue(SppUtamaModel::class.java)
                    if (data != null) {
                        listSpp.add(data)
                        tmplistSpp.add(
                            DataSppTemp(
                                0,
                                data.namaSpp + " Rp." + MainUtilities.localFormatNumber(data.nominal.toInt()),
                                data.masaBerlaku,
                                data.nominal,
                                data.namaSpp
                            )
                        )
                    }
                }

                adapterSpp.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        }

        sppRef.addValueEventListener(sppListener)
    }

    override fun showGrade() {
        val sppRef = FirebaseDatabase.getInstance().getReference(ApplicationConstant.kelas)
        listKelas.clear()
        tmplistKelas.clear()
        tmplistKelas.add(DataKelasTmp(0, "Pilih Kelas", "Kelas", "", ""))
        val kelasListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (h in dataSnapshot.children) {
                    val data = h.getValue(KelasModel::class.java)
                    if (data != null) {
                        listKelas.add(data)
                        tmplistKelas.add(
                            DataKelasTmp(
                                0,
                                data.tingkat + " " + data.namaKelas,
                                data.tingkat,
                                data.namaKelas,
                                data.KompetesiKeahlian
                            )
                        )
                    }

                }


                adapterKelas.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        }

        sppRef.addValueEventListener(kelasListener)
    }

    override fun showLoading() {
        if (!loading.isAdded)
            loading.show(childFragmentManager, LoadingDialogFragment::class.java.simpleName)
    }

    override fun hideLoading() {
        loading.dismiss()
    }
}
