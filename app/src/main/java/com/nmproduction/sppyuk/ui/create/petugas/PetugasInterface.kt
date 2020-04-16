package com.nmproduction.sppyuk.ui.create.petugas

import com.nmproduction.sppyuk.data.model.petugas.PetugasModel


interface PetugasInterface {
    interface View {
        fun showWarning(message: String)
        fun showLoading()
        fun hideLoading()
        fun createSuccess(message: String)
    }

    interface Presenter {
    fun createPetugas(petugasModel: PetugasModel)
    }
}