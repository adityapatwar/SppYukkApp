package com.nmproduction.sppyuk.ui.list.spp

import com.nmproduction.sppyuk.data.model.spp.utama.SppUtamaModel


interface ListSppInterface {
    interface View {
        fun showWarning(message: String)
        fun showData(kelasModel: List<SppUtamaModel?>?)
        fun messageDelete(message: String)
    }

    interface Presenter {
        fun getData()
        fun removeData(data: SppUtamaModel)

    }
}