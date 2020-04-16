package com.nmproduction.sppyuk.ui.history.fragment

import com.nmproduction.sppyuk.data.model.pembayaran.PembayaranModel


interface HistoryInterface {
    interface View {
        fun showWarning(message: String)
        fun showLoading()
        fun hideLoading()
        fun showData(data: List<PembayaranModel?>?)
    }

    interface Presenter {
        fun getData()
    }
}