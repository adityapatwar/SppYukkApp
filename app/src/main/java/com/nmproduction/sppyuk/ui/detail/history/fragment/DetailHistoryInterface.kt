package com.nmproduction.sppyuk.ui.detail.history.fragment

import com.nmproduction.sppyuk.data.model.pembayaran.PembayaranModel


interface DetailHistoryInterface {
    interface View {
        fun showWarning(message: String)
        fun showLoading()
        fun hideLoading()
        fun showData(data: PembayaranModel)
    }

    interface Presenter {
        fun getData()
    }
}