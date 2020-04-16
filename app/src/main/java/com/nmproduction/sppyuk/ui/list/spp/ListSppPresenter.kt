package com.nmproduction.sppyuk.ui.list.spp

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.nmproduction.sppyuk.data.model.spp.utama.SppUtamaModel


class ListSppPresenter(
    private val view: ListSppInterface.View,
    private val ref: DatabaseReference
) : ListSppInterface.Presenter {
    override fun getData() {

        val data: MutableList<SppUtamaModel?> = mutableListOf()

        val listener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (h in dataSnapshot.children) {
                    val it = h.getValue(SppUtamaModel::class.java)
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

    override fun removeData(data: SppUtamaModel) {
        ref.child(data.id).removeValue().addOnSuccessListener {
            view.messageDelete("Data Berhasil Dihapus")
        }
    }


}