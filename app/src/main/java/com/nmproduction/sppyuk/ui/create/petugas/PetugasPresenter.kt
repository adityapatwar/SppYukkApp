package com.nmproduction.sppyuk.ui.create.petugas

import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.nmproduction.sppyuk.data.model.petugas.PetugasModel


class PetugasPresenter(
    private val view: PetugasInterface.View,
    private val ref: DatabaseReference
) : PetugasInterface.Presenter {
    override fun createPetugas(petugasModel: PetugasModel) {
        view.showLoading()
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(petugasModel.email, petugasModel.password)
            .addOnCompleteListener {
                it.addOnSuccessListener {
                    FirebaseAuth.getInstance()
                        .signInWithEmailAndPassword(petugasModel.email, petugasModel.password)
                        .addOnSuccessListener {
                            petugasModel.id = it.user!!.uid
                            ref.child(petugasModel.id).setValue(petugasModel).addOnCompleteListener {
                                view.createSuccess("Berhasil ditambah")
                                view.hideLoading()
                            }
                        }
                }
            }
            .addOnFailureListener {
                view.showWarning(it.message.toString())
                view.hideLoading()
            }
    }


}