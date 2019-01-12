package vlab.android.architecture.repository;

import io.reactivex.Observable;
import vlab.android.architecture.repository.source.remote.response.PagingDataResponse;
import vlab.android.architecture.repository.source.remote.response.RepositoryResponse;

/**
* Created by Vinh Tran on 2/7/18.
*/

public interface GithubRepository {
    Observable<PagingDataResponse<RepositoryResponse>> getUserRepositories(
            String authorization,
            String sort,
            String pageIndex,
            String perPage
    );
}
