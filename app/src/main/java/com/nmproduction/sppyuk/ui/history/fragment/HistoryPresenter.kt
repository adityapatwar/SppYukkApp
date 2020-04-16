package com.nmproduction.sppyuk.ui.history.fragment

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.nmproduction.sppyuk.data.model.kelas.KelasModel
import com.nmproduction.sppyuk.data.model.pembayaran.PembayaranModel


class HistoryPresenter(
    private val view: HistoryInterface.View,
    private val ref: DatabaseReference
) : HistoryInterface.Presenter {
    override fun getData() {

        val data: MutableList<PembayaranModel?> = mutableListOf()

        val listener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (h in dataSnapshot.children) {
                    val it = h.getValue(PembayaranModel::class.java)
                    if (it != null) {
                        data.add(it)
                    }
                }
                view.showData(data)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                view.showWarning(databaseError.message)
            }
        }

        ref.addValueEventListener(listener)
    }


}