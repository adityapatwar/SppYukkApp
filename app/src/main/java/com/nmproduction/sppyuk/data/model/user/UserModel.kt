package com.nmproduction.sppyuk.data.model.user

import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("uid")
    var uid: String?,
    @SerializedName("username")
    val username: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("password")
    val password: String?,
    @SerializedName("level")
    val level: String?,
    @SerializedName("isAuthenticated")
    var isAuthenticated: Boolean?

)
