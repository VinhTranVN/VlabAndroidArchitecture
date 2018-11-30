package vlab.android.architecture.repository;

import io.reactivex.Observable;
import vlab.android.architecture.repository.source.remote.response.UserResponse;

/**
* Created by Vinh Tran on 2/7/18.
*/

public interface LoginRepository {
    Observable<UserResponse> login(String userName, String pwd);
}
