package vlab.android.architecture.data.repository;

import rx.Observable;
import vlab.android.architecture.model.UserInfo;

/**
* Created by Vinh Tran on 2/7/18.
*/

public interface LoginRepository {
    Observable<UserInfo> login(String userName, String pwd);
}
