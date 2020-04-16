package com.nmproduction.sppyuk.data.model.petugas

import com.google.gson.annotations.SerializedName

data class PetugasModel(
    @SerializedName("id")
    var id: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("nama")
    var nama: String

){
    constructor() : this("","","",""){

    }
}
