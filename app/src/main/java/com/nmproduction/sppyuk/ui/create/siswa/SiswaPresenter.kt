package com.nmproduction.sppyuk.ui.create.siswa

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.nmproduction.sppyuk.data.model.siswa.SiswaModel


class SiswaPresenter(
    private val view: SiswaInterface.View,
    private val ref: DatabaseReference
) : SiswaInterface.Presenter {
    override fun createSiswa(siswaModel: SiswaModel) {
        view.showLoading()
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(siswaModel.email, siswaModel.password)
            .addOnCompleteListener {
                it.addOnSuccessListener {
                    FirebaseAuth.getInstance()
                        .signInWithEmailAndPassword(siswaModel.email, siswaModel.password)
                        .addOnSuccessListener {
                            siswaModel.id = it.user!!.uid
                            ref.child(siswaModel.id).setValue(siswaModel).addOnCompleteListener {
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