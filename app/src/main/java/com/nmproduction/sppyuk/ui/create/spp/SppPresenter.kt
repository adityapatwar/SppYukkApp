package com.nmproduction.sppyuk.ui.create.spp

import com.google.firebase.database.DatabaseReference
import com.nmproduction.sppyuk.data.model.spp.utama.SppUtamaModel


class SppPresenter(
    private val view: SppInterface.View,
    private val ref: DatabaseReference
) : SppInterface.Presenter {
    override fun create(sppUtamaModel: SppUtamaModel) {
        view.showLoading()
        sppUtamaModel.id = ref.push().key.toString()
        ref.child(sppUtamaModel.id).setValue(sppUtamaModel).addOnCompleteListener {
            view.createSuccess("Berhasil ditambah")
            view.hideLoading()

        }.addOnFailureListener {
            view.showWarning(it.message.toString())
            view.hideLoading()
        }
    }


}