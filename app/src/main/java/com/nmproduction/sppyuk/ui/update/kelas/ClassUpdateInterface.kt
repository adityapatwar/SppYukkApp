package com.nmproduction.sppyuk.ui.update.kelas

import com.nmproduction.sppyuk.data.model.kelas.KelasModel


interface ClassUpdateInterface {
    interface View {
        fun showWarning(message: String)
        fun showLoading()
        fun hideLoading()
        fun showData(kelasModel: KelasModel)
        fun updateSuccess(message: String)
    }

    interface Presenter {

        fun updateGrade(kelasModel:KelasModel)
        fun getData(id: String)

    }
}