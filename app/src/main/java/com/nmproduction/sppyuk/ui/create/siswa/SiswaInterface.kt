package com.nmproduction.sppyuk.ui.create.siswa

import com.nmproduction.sppyuk.data.model.siswa.SiswaModel


interface SiswaInterface {
    interface View {
        fun showWarning(message: String)
        fun createSuccess(message: String)
        fun showSpp()
        fun showGrade()
        fun showLoading()
        fun hideLoading()
    }

    interface Presenter {
        fun createSiswa(siswaModel: SiswaModel)

    }
}