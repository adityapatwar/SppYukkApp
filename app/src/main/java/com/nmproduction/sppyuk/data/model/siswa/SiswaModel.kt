package com.nmproduction.sppyuk.data.model.siswa

import com.google.gson.annotations.SerializedName

data class SiswaModel(
    @SerializedName("id")
    var id: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("nama")
    var nama: String,
    @SerializedName("nisn")
    var nisn: String,
    @SerializedName("nis")
    var nis: String,
    @SerializedName("kelas")
    var kelas: DataKelas,
    @SerializedName("alamat")
    var alamat: String,
    @SerializedName("noTelp")
    var noTelp: String,
    @SerializedName("Spp")
    var Spp: DataSPP

) {
    constructor() : this("", "", "", "", "", "", DataKelas(), "", "", DataSPP()) {}
}


data class DataKelas(
    @SerializedName("tingkat")
    val tingkat: String,
    @SerializedName("namaKelas")
    val namaKelas: String,
    @SerializedName("kompetesiKeahlian")
    val kompetesiKeahlian: String
) {
    constructor() : this("", "", "") {}
}

data class DataSPP(
    @SerializedName("sisaPembayaran")
    val sisaPembayaran: String,
    @SerializedName("tenggatWaktu")
    val tenggatWaktu: String
) {
    constructor() : this("", "") {}
}



data class SiswaModelTemp(
    @SerializedName("id")
    var id: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("nama")
    var nama: String?,
    @SerializedName("nisn")
    var nisn: String,
    @SerializedName("nis")
    var nis: String,
    @SerializedName("kelas")
    var kelas: DataKelasTemp,
    @SerializedName("alamat")
    var alamat: String,
    @SerializedName("noTelp")
    var noTelp: String,
    @SerializedName("Spp")
    var Spp: DataSPPTemp

){
    override fun toString(): String = nama!!
}


data class DataKelasTemp(
    @SerializedName("tingkat")
    val tingkat: String,
    @SerializedName("namaKelas")
    val namaKelas: String,
    @SerializedName("kompetesiKeahlian")
    val kompetesiKeahlian: String
)

data class DataSPPTemp(
    @SerializedName("sisaPembayaran")
    var sisaPembayaran: String,
    @SerializedName("tenggatWaktu")
    val tenggatWaktu: String
)

