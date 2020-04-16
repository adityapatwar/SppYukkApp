package com.nmproduction.sppyuk.data.model.kelas

import com.google.gson.annotations.SerializedName

data class KelasModel(
    @SerializedName("id")
    var id: String,
    @SerializedName("tingkat")
    val tingkat: String,
    @SerializedName("namaKelas")
    val namaKelas: String,
    @SerializedName("KompetesiKeahlian")
    var KompetesiKeahlian: String

){
    constructor() : this("","","",""){

    }

}

data class DataKelasTmp(
    @SerializedName("id")
    var id: Int?,
    @SerializedName("nama")
    var nama: String?,
    @SerializedName("tingkat")
    val tingkat: String,
    @SerializedName("namaKelas")
    val namaKelas: String,
    @SerializedName("KompetesiKeahlian")
    var KompetesiKeahlian: String
) {
    override fun toString(): String = nama!!
}




