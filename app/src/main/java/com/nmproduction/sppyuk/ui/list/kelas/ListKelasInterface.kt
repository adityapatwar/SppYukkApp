package com.nmproduction.sppyuk.ui.list.kelas

import com.nmproduction.sppyuk.data.model.kelas.KelasModel


interface ListKelasInterface {
    interface View {
        fun showWarning(message: String)
        fun showData(kelasModel: List<KelasModel?>?)
        fun messageDelete(message: String)
    }

    interface Presenter {
        fun getData()
        fun removeData(kelasModel: KelasModel)
    }
}