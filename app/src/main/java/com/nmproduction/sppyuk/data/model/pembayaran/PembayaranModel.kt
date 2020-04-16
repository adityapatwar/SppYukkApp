package com.nmproduction.sppyuk.data.model.pembayaran

import com.google.gson.annotations.SerializedName

data class PembayaranModel(
    @SerializedName("id")
    var id: String,
    @SerializedName("namaPetugas")
    val namaPetugas: String,
    @SerializedName("namaSiswa")
    val namaSiswa: String,
    @SerializedName("nisn")
    val nisn: String,
    @SerializedName("idSiswa")
    val idSiswa: String,
    @SerializedName("tanggalBayar")
    val tanggalBayar: String,
    @SerializedName("sisaPembayaran")
    val sisaPembayaran: String,
    @SerializedName("jumlah")
    val jumlah: String
) {
    constructor() : this("", "", "", "", "","","", "") {}
}
