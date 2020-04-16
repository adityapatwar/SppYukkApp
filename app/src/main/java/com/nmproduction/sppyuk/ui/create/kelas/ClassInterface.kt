package com.nmproduction.sppyuk.ui.create.kelas

import com.nmproduction.sppyuk.data.model.kelas.KelasModel


interface ClassInterface {
    interface View {
        fun showWarning(message: String)
        fun showLoading()
        fun hideLoading()
        fun createSuccess(message: String)
    }

    interface Presenter {

        fun createGrade(kelasModel:KelasModel)

    }
}