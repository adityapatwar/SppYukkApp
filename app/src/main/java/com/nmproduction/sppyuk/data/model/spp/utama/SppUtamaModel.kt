package com.nmproduction.sppyuk.data.model.spp.utama

import com.google.gson.annotations.SerializedName

class SppUtamaModel(
    @SerializedName("id")
    var id: String,
    @SerializedName("masaBerlaku")
    val masaBerlaku: String,
    @SerializedName("nominal")
    val nominal: String,
    @SerializedName("namaSpp")
    var namaSpp: String

) {
    constructor() : this("", "", "", "") {

    }
}

data class DataSppTemp(
    @SerializedName("id")
    var id: Int?,
    @SerializedName("nama")
    var nama: String?,
    @SerializedName("masaBerlaku")
    val masaBerlaku: String,
    @SerializedName("nominal")
    val nominal: String,
    @SerializedName("namaSpp")
    var namaSpp: String
) {
    override fun toString(): String = nama!!
}

