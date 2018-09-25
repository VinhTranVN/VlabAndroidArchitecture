package vlab.android.architecture.data

import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Vinh.Tran on 8/6/18.
 **/
interface MockFriendsApi {

    @GET("friends")
    fun getAllFriends(): Call<List<UserResponse>>

}