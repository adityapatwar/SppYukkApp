package com.nmproduction.sppyuk.ui.list.petugas

import com.nmproduction.sppyuk.data.model.petugas.PetugasModel


interface ListPetugasInterface {
    interface View {
        fun showWarning(message: String)
        fun showData(kelasModel: List<PetugasModel?>?)
        fun messageDelete(message: String)
    }

    interface Presenter {
        fun getData()
        fun removeData(data: PetugasModel)

    }
}