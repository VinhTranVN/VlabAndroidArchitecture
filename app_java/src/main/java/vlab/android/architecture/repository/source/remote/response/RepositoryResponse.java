package vlab.android.architecture.repository.source.remote.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vinh.Tran on 11/30/18.
 **/
public class RepositoryResponse {
    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }
}
