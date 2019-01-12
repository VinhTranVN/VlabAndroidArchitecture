package vlab.android.architecture.repository.source.remote;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import vlab.android.architecture.repository.source.remote.response.RepositoryResponse;
import vlab.android.architecture.repository.source.remote.response.UserResponse;

public interface GitHubApi {
    @GET("user")
    Observable<UserResponse> login(@Header("Authorization") String authorization);

    @GET("user/repos")
    Observable<Response<List<RepositoryResponse>>> getRepositories(
            @Header("Authorization") String authorization,
            @Query("sort") String sort,
            @Query("page") String pageIndex,
            @Query("per_page") String perPage
    );
}
