package vlab.android.architecture.repository

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

/**
 * Created by Vinh.Tran on 8/6/18.
 **/
interface GithubApi {

    @GET("user")
    fun login(@Header("Authorization") auth : String): Call<UserResponse>

}