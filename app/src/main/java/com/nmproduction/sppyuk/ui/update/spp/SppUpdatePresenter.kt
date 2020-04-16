package com.nmproduction.sppyuk.ui.update.spp

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.nmproduction.sppyuk.data.model.siswa.SiswaModel
import com.nmproduction.sppyuk.data.model.spp.utama.SppUtamaModel


class SppUpdatePresenter(
    private val view: SppUpdateInterface.View,
    private val ref: DatabaseReference
) : SppUpdateInterface.Presenter {
    override fun updateSiswa(sppUtamaModel: SppUtamaModel) {

        ref.child(sppUtamaModel.id).setValue(sppUtamaModel).addOnCompleteListener {
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
                val it = dataSnapshot.getValue(SppUtamaModel::class.java)
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