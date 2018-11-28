package vlab.android.architecture.repository.source.remote.response

import com.google.gson.annotations.SerializedName

/**
 * Created by Vinh.Tran on 10/20/16.
 */
class UserResponse {
    @SerializedName("login")
    var login: String? = null

    @SerializedName("id")
    var id: Int? = null

    @SerializedName("avatar_url")
    var avatarUrl: String? = null

    @SerializedName("url")
    var url: String? = null

    @SerializedName("type")
    var type: String? = null

    @SerializedName("site_admin")
    var siteAdmin: Boolean? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("company")
    var company: String? = null

    @SerializedName("blog")
    var blog: String? = null

    @SerializedName("location")
    var location: String? = null

    @SerializedName("email")
    var email: String? = null
}
