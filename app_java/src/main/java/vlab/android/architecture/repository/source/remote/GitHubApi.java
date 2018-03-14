package vlab.android.architecture.repository.source.remote;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import vlab.android.architecture.repository.source.remote.response.UserResponse;

public interface GitHubApi {
    @GET("user")
    Observable<UserResponse> login(@Header("Authorization") String authorization);
}
