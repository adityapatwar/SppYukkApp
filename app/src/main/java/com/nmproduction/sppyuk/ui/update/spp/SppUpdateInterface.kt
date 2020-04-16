package com.nmproduction.sppyuk.ui.update.spp

import com.nmproduction.sppyuk.data.model.spp.utama.SppUtamaModel


interface SppUpdateInterface {
    interface View {
        fun showWarning(message: String)
        fun showLoading()
        fun hideLoading()
        fun showData(sppUtamaModel: SppUtamaModel)
        fun updateSuccess(message: String)
    }

    interface Presenter {
        fun updateSiswa(sppUtamaModel: SppUtamaModel)
        fun getData(id: String)
    }
}