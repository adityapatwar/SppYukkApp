package com.nmproduction.sppyuk.ui.create.kelas

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.nmproduction.sppyuk.data.model.kelas.KelasModel


class ClassPresenter(
    private val view: ClassInterface.View,
    private val ref: DatabaseReference
) : ClassInterface.Presenter {

    override fun createGrade(kelasModel: KelasModel) {
        view.showLoading()
        kelasModel.id = ref.push().key.toString()
        ref.child(kelasModel.id).setValue(kelasModel).addOnCompleteListener {
            view.createSuccess("Berhasil ditambah")
            view.hideLoading()

        }.addOnFailureListener {
            view.showWarning(it.message.toString())
            view.hideLoading()
        }
    }



}