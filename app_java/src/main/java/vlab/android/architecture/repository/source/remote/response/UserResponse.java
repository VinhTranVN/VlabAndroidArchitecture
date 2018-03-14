package vlab.android.architecture.repository.source.remote.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vinh.Tran on 10/20/16.
 */
public class UserResponse {
    @SerializedName("login")
    private String login;
    @SerializedName("id")
    private Integer id;
    @SerializedName("avatar_url")
    private String avatarUrl;
    @SerializedName("url")
    private String url;
    @SerializedName("type")
    private String type;
    @SerializedName("site_admin")
    private Boolean siteAdmin;
    @SerializedName("name")
    private String name;
    @SerializedName("company")
    private String company;
    @SerializedName("blog")
    private String blog;
    @SerializedName("location")
    private String location;
    @SerializedName("email")
    private String email;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getSiteAdmin() {
        return siteAdmin;
    }

    public void setSiteAdmin(Boolean siteAdmin) {
        this.siteAdmin = siteAdmin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
