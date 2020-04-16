package com.nmproduction.sppyuk.ui.update.petugas

import com.nmproduction.sppyuk.data.model.kelas.KelasModel
import com.nmproduction.sppyuk.data.model.petugas.PetugasModel


interface PetugasUpdateInterface {
    interface View {
        fun showWarning(message: String)
        fun showLoading()
        fun hideLoading()
        fun showData(petugasModel: PetugasModel)
        fun updateSuccess(message: String)
    }

    interface Presenter {
        fun updatePetugas(petugasModel: PetugasModel)
        fun getData(id: String)
    }
}