package com.nmproduction.sppyuk.ui.update.siswa

import com.nmproduction.sppyuk.data.model.petugas.PetugasModel
import com.nmproduction.sppyuk.data.model.siswa.SiswaModel


interface SiswaUpdateInterface {
    interface View {
        fun showWarning(message: String)
        fun showLoading()
        fun hideLoading()
        fun showData(siswaModel: SiswaModel)
        fun updateSuccess(message: String)
    }

    interface Presenter {
        fun updateSiswa(siswaModel: SiswaModel)
        fun getData(id: String)
    }
}