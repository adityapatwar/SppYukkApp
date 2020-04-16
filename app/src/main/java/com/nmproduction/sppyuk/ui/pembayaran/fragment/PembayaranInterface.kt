package com.nmproduction.sppyuk.ui.pembayaran.fragment

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.nmproduction.sppyuk.data.model.pembayaran.PembayaranModel
import com.nmproduction.sppyuk.data.model.siswa.SiswaModel
import com.nmproduction.sppyuk.data.model.siswa.SiswaModelTemp


interface PembayaranInterface {
    interface View {
        fun showWarning(message: String)
        fun createSuccess(message: String)
        fun showSiswa(data: List<SiswaModel?>?,dataTemp: List<SiswaModelTemp?>?)
        fun showLoading()
        fun hideLoading()
    }

    interface Presenter {
        fun createPembayaran(data: PembayaranModel,ref : DatabaseReference,ref2: DatabaseReference)
        fun getDataSiswa(ref: DatabaseReference)
        fun update(ref: DatabaseReference,data : SiswaModelTemp)
    }
}