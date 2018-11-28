package vlab.android.architecture.repository;

import io.reactivex.Observable;
import vlab.android.architecture.model.UserInfo;

/**
* Created by Vinh Tran on 2/7/18.
*/

public interface LoginRepository {
    Observable<UserInfo> login(String userName, String pwd);
}
