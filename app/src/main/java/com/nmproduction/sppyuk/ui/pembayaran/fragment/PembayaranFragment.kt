package com.nmproduction.sppyuk.ui.pembayaran.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.core.widget.addTextChangedListener
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

import com.nmproduction.sppyuk.R
import com.nmproduction.sppyuk.data.constant.ApplicationConstant
import com.nmproduction.sppyuk.data.model.pembayaran.PembayaranModel
import com.nmproduction.sppyuk.data.model.siswa.SiswaModel
import com.nmproduction.sppyuk.data.model.siswa.SiswaModelTemp
import com.nmproduction.sppyuk.data.preference.AlphaPreferences
import com.nmproduction.sppyuk.ui.base.BaseFragment
import com.nmproduction.sppyuk.ui.loading.LoadingDialogFragment
import com.nmproduction.sppyuk.ui.pembayaran.fragment.adapter.NamaSiswaAdapter
import kotlinx.android.synthetic.main.fragment_pembayaran.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class PembayaranFragment : BaseFragment(), PembayaranInterface.View {


    lateinit var presenter: PembayaranPresenter
    var listsiswa: MutableList<SiswaModel?> = ArrayList()
    var listsiswaTemp: MutableList<SiswaModelTemp?> = ArrayList()
    private val loading = LoadingDialogFragment()

    private lateinit var adapterNamaSiswa: NamaSiswaAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pembayaran, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = PembayaranPresenter(
            this
        )

        adapterNamaSiswa =
            NamaSiswaAdapter(view.context, R.layout.spinner_dropdown_item, listsiswaTemp)

        spSiswa.adapter = adapterNamaSiswa

        presenter.getDataSiswa(FirebaseDatabase.getInstance().getReference(ApplicationConstant.siswa))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val alphaPreferences = AlphaPreferences(requireContext())

        if (alphaPreferences.user.equals("")) {
            petugas.setText("Admin")
        } else {
            petugas.setText(alphaPreferences.user)
        }

        spSiswa.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (!listsiswaTemp.isNullOrEmpty()) {
                    val data: SiswaModelTemp = spSiswa.selectedItem as SiswaModelTemp
                    val sdf = SimpleDateFormat("dd/M/yyyy")
                    val currentDate = sdf.format(Date())

                    spp.setText(data.Spp.tenggatWaktu)
                    date.setText(currentDate)
                    tunggak.setText(data.Spp.sisaPembayaran)
                    jumlah.addTextChangedListener {
                        var typeValue = jumlah.text.toString()
                        if (typeValue == "")
                            typeValue = "0"
                        if (typeValue.toLong() > data.Spp.sisaPembayaran.toLong()) {
                            jumlah.setText(data.Spp.sisaPembayaran)
                            showSnackBar("Nominal Sudah Maksimal", view!!)
                        }
                    }


                    create.setOnClickListener {
                        data.Spp.sisaPembayaran = (data.Spp.sisaPembayaran.toInt() - jumlah.text.toString().toInt()).toString()
                        presenter.createPembayaran(
                            PembayaranModel(
                                "",
                                petugas.text.toString(),
                                data.nama.toString(),
                                data.nisn,
                                data.id,
                                date.text.toString(),
                                data.Spp.sisaPembayaran,
                                jumlah.text.toString()
                            ),
                            FirebaseDatabase.getInstance().getReference(ApplicationConstant.pembayaran)
                            ,
                            FirebaseDatabase.getInstance().getReference(ApplicationConstant.siswa).child(
                                data.id
                            )
                        )

                        presenter.update(  FirebaseDatabase.getInstance().getReference(ApplicationConstant.siswa).child(
                            data.id
                        ),data)

                    }

                }
            }
        }


    }

    override fun showWarning(message: String) {
        showSnackBar(message, view!!)
    }

    override fun createSuccess(message: String) {
        showSnackBar(message, view!!)
        activity?.onBackPressed()
    }

    override fun showSiswa(data: List<SiswaModel?>?, dataTemp: List<SiswaModelTemp?>?) {
        listsiswa.clear()
        listsiswaTemp.clear()
        data?.let { listsiswa.addAll(it) }
        dataTemp?.let { listsiswaTemp.addAll(it) }
        adapterNamaSiswa.notifyDataSetChanged()
    }

    override fun showLoading() {
        if (!loading.isAdded)
            loading.show(childFragmentManager, LoadingDialogFragment::class.java.simpleName)
    }

    override fun hideLoading() {
        loading.dismiss()
    }

}
