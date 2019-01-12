package vlab.android.architecture.feature.user_repository;

import vlab.android.architecture.repository.source.remote.response.RepositoryResponse;

/**
 * Created by Vinh.Tran on 11/30/18.
 **/
public class RepositoryModel {
    private String repoName;
    private String repoDescription;

    public RepositoryModel(RepositoryResponse response) {
        repoName = response.getName();
        repoDescription = response.getDescription();
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public String getDescription() {
        return repoDescription;
    }
}
