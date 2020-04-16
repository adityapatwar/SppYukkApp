package com.nmproduction.sppyuk.ui.list.petugas

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.nmproduction.sppyuk.data.model.petugas.PetugasModel


class ListPetugasPresenter(
    private val view: ListPetugasInterface.View,
    private val ref: DatabaseReference
) : ListPetugasInterface.Presenter {
    override fun getData() {
        val data: MutableList<PetugasModel?> = mutableListOf()

        val listener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (h in dataSnapshot.children) {
                    val it = h.getValue(PetugasModel::class.java)
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

    override fun removeData(data: PetugasModel) {
        ref.child(data.id).removeValue().addOnSuccessListener {
            view.messageDelete("Data Berhasil Dihapus")
        }
    }


}