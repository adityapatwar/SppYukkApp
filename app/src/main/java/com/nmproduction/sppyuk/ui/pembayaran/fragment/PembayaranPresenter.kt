package com.nmproduction.sppyuk.ui.pembayaran.fragment

import com.google.firebase.database.*
import com.nmproduction.sppyuk.data.model.pembayaran.PembayaranModel
import com.nmproduction.sppyuk.data.model.siswa.DataKelasTemp
import com.nmproduction.sppyuk.data.model.siswa.DataSPPTemp
import com.nmproduction.sppyuk.data.model.siswa.SiswaModel
import com.nmproduction.sppyuk.data.model.siswa.SiswaModelTemp


class PembayaranPresenter(
    private val view: PembayaranInterface.View
) : PembayaranInterface.Presenter {


    override fun createPembayaran(
        data: PembayaranModel,
        ref: DatabaseReference,
        ref2: DatabaseReference
    ) {
        view.showLoading()
        data.id = ref.push().key.toString()
        ref.child(data.id).setValue(data).addOnCompleteListener {

            ref2.child("Pembayaran").child(data.id).setValue(data).addOnCompleteListener {
                view.createSuccess("Berhasil ditambah")
                view.hideLoading()

            }.addOnFailureListener {
                view.showWarning(it.message.toString())
                view.hideLoading()
            }


        }.addOnFailureListener {
            view.showWarning(it.message.toString())
            view.hideLoading()
        }
    }


    override fun getDataSiswa(ref: DatabaseReference) {

        val data: MutableList<SiswaModel?> = mutableListOf()
        val dataTemp: MutableList<SiswaModelTemp?> = mutableListOf()
        val listener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (h in dataSnapshot.children) {
                    val it = h.getValue(SiswaModel::class.java)
                    if (it != null) {
                        data.add(it)
                        dataTemp.add(
                            SiswaModelTemp(
                                it.id,
                                it.email,
                                it.password,
                                it.nama,
                                it.nisn,
                                it.nis,
                                DataKelasTemp(
                                    it.kelas.tingkat,
                                    it.kelas.namaKelas,
                                    it.kelas.kompetesiKeahlian
                                ),
                                it.alamat,
                                it.noTelp,
                                DataSPPTemp(it.Spp.sisaPembayaran, it.Spp.tenggatWaktu)
                            )
                        )
                    }
                }
                view.showSiswa(data, dataTemp)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                view.showWarning(databaseError.message)
            }
        }

        ref.addValueEventListener(listener)
    }

    override fun update(ref: DatabaseReference, data: SiswaModelTemp) {

        ref.setValue(data).addOnCompleteListener {
            it.addOnSuccessListener {

            }

            it.addOnFailureListener {

            }
        }
    }


}