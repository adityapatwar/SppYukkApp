package com.nmproduction.sppyuk.ui.detail.history.fragment

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.nmproduction.sppyuk.data.model.kelas.KelasModel
import com.nmproduction.sppyuk.data.model.pembayaran.PembayaranModel


class DetailHistoryPresenter(
    private val view: DetailHistoryInterface.View,
    private val ref: DatabaseReference
) : DetailHistoryInterface.Presenter {
    override fun getData() {
        val listener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val it = dataSnapshot.getValue(PembayaranModel::class.java)
                if (it != null) {
                    view.showData(it)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                view.showWarning(databaseError.message)
            }
        }

        ref.addValueEventListener(listener)
    }


}