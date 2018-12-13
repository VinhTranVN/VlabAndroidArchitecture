package vlab.android.architecture.repository.source.remote.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vinh.Tran on 11/30/18.
 **/
public class RepositoryResponse {
    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("owner")
    private UserResponse owner;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public UserResponse getOwner() {
        return owner;
    }
}
