package vlab.android.architecture.feature.user_repository;

import vlab.android.architecture.repository.source.remote.response.RepositoryResponse;

/**
 * Created by Vinh.Tran on 11/30/18.
 **/
public class UserRepositoryModel {
    private String repoName;

    public UserRepositoryModel(RepositoryResponse response) {
        repoName = response.getName();
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }
}
