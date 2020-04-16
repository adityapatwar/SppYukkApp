package com.nmproduction.sppyuk.ui.update.kelas

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.nmproduction.sppyuk.data.model.kelas.KelasModel


class ClassUpdatePresenter(
    private val view: ClassUpdateInterface.View,
    private val ref: DatabaseReference
) : ClassUpdateInterface.Presenter {

    override fun updateGrade(kelasModel: KelasModel) {
        ref.child(kelasModel.id).setValue(kelasModel).addOnCompleteListener {
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
                val it = dataSnapshot.getValue(KelasModel::class.java)
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