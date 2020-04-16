package com.nmproduction.sppyuk.ui.list.siswa

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.nmproduction.sppyuk.data.model.siswa.SiswaModel


class ListSiswaPresenter(
    private val view: ListSiswaInterface.View,
    private val ref: DatabaseReference
) : ListSiswaInterface.Presenter {
    override fun getData() {

        val data: MutableList<SiswaModel?> = mutableListOf()

        val listener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (h in dataSnapshot.children) {
                    val it = h.getValue(SiswaModel::class.java)
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

    override fun removeData(data: SiswaModel) {
        ref.child(data.id).removeValue().addOnSuccessListener {
            view.messageDelete("Data Berhasil Dihapus")
        }
    }


}