package vlab.android.architecture.data.source.remote;

import retrofit2.http.GET;
import retrofit2.http.Header;
import rx.Observable;
import vlab.android.architecture.data.source.remote.response.UserResponse;

public interface GitHubApi {
    @GET("user")
    Observable<UserResponse> login(@Header("Authorization") String authorization);
}
