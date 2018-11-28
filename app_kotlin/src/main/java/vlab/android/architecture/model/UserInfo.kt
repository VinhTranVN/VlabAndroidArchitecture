package vlab.android.architecture.model

import vlab.android.architecture.repository.source.remote.response.UserResponse

/**
 * Created by Vinh Tran on 2/15/18.
 */

class UserInfo {

    var userName: String?

    constructor(userResponse: UserResponse) {
        this.userName = userResponse.name
    }
}
