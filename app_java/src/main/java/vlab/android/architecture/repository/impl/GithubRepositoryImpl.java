package vlab.android.architecture.repository.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.Headers;
import vlab.android.architecture.repository.GithubRepository;
import vlab.android.architecture.repository.source.remote.GitHubApi;
import vlab.android.architecture.repository.source.remote.response.PagingDataResponse;
import vlab.android.architecture.repository.source.remote.response.RepositoryResponse;
import vlab.android.common.util.LogUtils;

/**
 * Created by Vinh.Tran on 11/30/18.
 **/
public class GithubRepositoryImpl implements GithubRepository {

    private GitHubApi mApi;

    @Inject
    public GithubRepositoryImpl(GitHubApi api) {
        mApi = api;
    }

    @Override
    public Observable<PagingDataResponse<RepositoryResponse>> getUserRepositories(String authorization, String sort, String pageIndex, String perPage) {
        return mApi.getRepositories(authorization,sort, pageIndex, perPage)
                .doOnNext(dataResponse -> {
                    LogUtils.d(getClass().getSimpleName(), ">>> getUserRepositories: doOnNext dataResponse.isSuccessful() " + dataResponse.isSuccessful());
                    if(!dataResponse.isSuccessful()){
                        throw new Exception(dataResponse.errorBody().string());
                    }
                })
                .map(dataResponse -> {
                    Headers headers = dataResponse.headers();
                    String nextSince = getNextPage(headers.get("link"));
                    LogUtils.d(getClass().getSimpleName(), ">>> nextPage: " + nextSince);
                    List<RepositoryResponse> body = dataResponse.body();

                    return new PagingDataResponse<>(body, nextSince);
                });
    }

    public String getNextPage(String headerLink) {
        if(headerLink == null){
            return null;
        }

        Pattern pattern = Pattern.compile("page=(\\d*)[&per_page=\\d>; ]+rel=\"next\"");
        Matcher matcher = pattern.matcher(headerLink);
        if (matcher.find()) {
            return matcher.group(1); // return next since value
        }

        return null;
    }
}
