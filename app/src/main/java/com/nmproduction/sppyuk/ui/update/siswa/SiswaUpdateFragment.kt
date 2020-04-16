package com.nmproduction.sppyuk.ui.update.siswa

import android.os.Bundle
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
import com.nmproduction.sppyuk.ui.create.siswa.SiswaFragment
import com.nmproduction.sppyuk.ui.create.siswa.SiswaPresenter
import com.nmproduction.sppyuk.ui.create.siswa.adapter.KelasAdapter
import com.nmproduction.sppyuk.ui.create.siswa.adapter.SppAdapter
import com.nmproduction.sppyuk.ui.loading.LoadingDialogFragment
import com.nmproduction.sppyuk.utils.MainUtilities
import kotlinx.android.synthetic.main.fragment_siswa.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class SiswaUpdateFragment : BaseFragment(),SiswaUpdateInterface.View {


    companion object {
        fun newInstance(id: String): SiswaUpdateFragment {
            val fragment = SiswaUpdateFragment()
            val bundle = Bundle()
            bundle.putString(ApplicationConstant.id, id)
            fragment.arguments = bundle
            return fragment
        }
    }

    lateinit var presenter: SiswaUpdatePresenter
    var listKelas: MutableList<KelasModel> = ArrayList()
    var listSpp: MutableList<SppUtamaModel> = ArrayList()
    private val loading = LoadingDialogFragment()

    private lateinit var dataKelas : DataKelas
    private lateinit var  dataSpp : DataSPP


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
        presenter = SiswaUpdatePresenter(
            this,
            FirebaseDatabase.getInstance().getReference(ApplicationConstant.siswa)
        )

        adapterSpp =
            SppAdapter(view.context, R.layout.spinner_dropdown_item, tmplistSpp)
        adapterKelas =
            KelasAdapter(view.context, R.layout.spinner_dropdown_item, tmplistKelas)

        spSpp.adapter = adapterSpp
        spKelas.adapter = adapterKelas


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.getString(ApplicationConstant.id,"")?.let { presenter.getData(it) }

        create.setOnClickListener {
            val spp: DataSppTemp = spSpp.selectedItem as DataSppTemp
            val kelas: DataKelasTmp = spKelas.selectedItem as DataKelasTmp


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

                val siswaModel = SiswaModel(
                    arguments?.getString(ApplicationConstant.id).toString(),
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
                presenter.updateSiswa(siswaModel)
            }
        }

    }

    override fun showWarning(message: String) {
        showSnackBar(message, view!!)
    }


    override fun showLoading() {
        if (!loading.isAdded)
            loading.show(childFragmentManager, LoadingDialogFragment::class.java.simpleName)
    }

    override fun hideLoading() {
        loading.dismiss()
    }

    override fun showData(siswaModel: SiswaModel) {
        username.setText(siswaModel.email)
        username.isEnabled = false
        password.setText(siswaModel.password)
        password.isEnabled = false
        name.setText(siswaModel.nama)
        nisn.setText(siswaModel.nisn)
        nis.setText(siswaModel.nis)
        dataKelas = DataKelas(siswaModel.kelas.tingkat,siswaModel.kelas.namaKelas,siswaModel.kelas.kompetesiKeahlian)
        alamat.setText(siswaModel.alamat)
        noTel.setText(siswaModel.noTelp)
        dataSpp = DataSPP(siswaModel.Spp.sisaPembayaran,siswaModel.Spp.tenggatWaktu)

        tmplistSpp.add(DataSppTemp(0,"SPP","SPP","SPP","SPP"))
        tmplistKelas.add(DataKelasTmp(0,siswaModel.kelas.namaKelas,siswaModel.kelas.namaKelas,siswaModel.kelas.namaKelas,siswaModel.kelas.namaKelas))
        adapterSpp.notifyDataSetChanged()
        adapterKelas.notifyDataSetChanged()

        spKelas.isEnabled = false
        spSpp.isEnabled = false

        textOne.setText("Ubah")
    }

    override fun updateSuccess(message: String) {
        showSnackBar(message, view!!)
    }

}
