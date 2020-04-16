package com.nmproduction.sppyuk.ui.update.petugas

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.nmproduction.sppyuk.data.model.kelas.KelasModel
import com.nmproduction.sppyuk.data.model.petugas.PetugasModel


class PetugasUpdatePresenter(
    private val view: PetugasUpdateInterface.View,
    private val ref: DatabaseReference
) : PetugasUpdateInterface.Presenter {
    override fun updatePetugas(petugasModel: PetugasModel) {
        ref.child(petugasModel.id).setValue(petugasModel).addOnCompleteListener {
            it.addOnSuccessListener {
                view.updateSuccess("Update Berhasil")
            }

            it.addOnFailureListener {
                view.showWarning(it.message.toString())
            }
        }
    }

    override fun getData(id: String) {

        val listener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val it = dataSnapshot.getValue(PetugasModel::class.java)
                if (it != null) {
                    view.showData(it)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        }

        ref.child(id).addValueEventListener(listener)

    }


}