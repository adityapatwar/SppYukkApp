package com.nmproduction.sppyuk.ui.list.siswa

import com.nmproduction.sppyuk.data.model.siswa.SiswaModel


interface ListSiswaInterface {
    interface View {
        fun showWarning(message: String)
        fun showData(kelasModel: List<SiswaModel?>?)
        fun messageDelete(message: String)
    }

    interface Presenter {
        fun getData()
        fun removeData(data: SiswaModel)
    }
}