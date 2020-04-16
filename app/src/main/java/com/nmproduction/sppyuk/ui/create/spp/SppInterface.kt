package com.nmproduction.sppyuk.ui.create.spp

import com.nmproduction.sppyuk.data.model.spp.utama.SppUtamaModel


interface SppInterface {
    interface View {
        fun showWarning(message: String)
        fun createSuccess(message: String)
        fun showLoading()
        fun hideLoading()
    }

    interface Presenter {
        fun create(sppUtamaModel: SppUtamaModel)
    }
}