package vlab.android.architecture.repository.impl

import android.util.Base64
import io.reactivex.Observable
import vlab.android.architecture.feature.user.UserInfo
import vlab.android.architecture.repository.LoginRepository
import vlab.android.architecture.repository.source.remote.GitHubApi
import javax.inject.Inject

/**
 * Created by Vinh Tran on 2/14/18.
 */

class LoginRepositoryImpl : LoginRepository {

    private val mApi: GitHubApi

    @Inject constructor(mApi: GitHubApi) {
        this.mApi = mApi
    }

    override fun login(userName: String, pwd: String): Observable<UserInfo> {
        val auth = "$userName:$pwd"
        //LogUtils.println(">>> login: " + auth);
        return mApi.login("Basic " + Base64.encodeToString(auth.toByteArray(), Base64.NO_WRAP))
                .map { userResponse -> UserInfo(userResponse) }
    }
}
