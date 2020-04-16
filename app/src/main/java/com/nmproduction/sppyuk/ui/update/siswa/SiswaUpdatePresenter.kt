package com.nmproduction.sppyuk.ui.update.siswa

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.nmproduction.sppyuk.data.model.petugas.PetugasModel
import com.nmproduction.sppyuk.data.model.siswa.SiswaModel


class SiswaUpdatePresenter(
    private val view: SiswaUpdateInterface.View,
    private val ref: DatabaseReference
) : SiswaUpdateInterface.Presenter {

    override fun updateSiswa(siswaModel: SiswaModel) {
        ref.child(siswaModel.id).setValue(siswaModel).addOnCompleteListener {
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
                val it = dataSnapshot.getValue(SiswaModel::class.java)
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