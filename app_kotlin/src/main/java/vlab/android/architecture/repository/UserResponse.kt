package vlab.android.architecture.repository

import com.google.gson.annotations.SerializedName

open class UserResponse(name: String, avatarUrl : String) {

    @SerializedName("name")
    val name : String? = null

    @SerializedName("avatar_url")
    val avatarUrl : String? = null

}
