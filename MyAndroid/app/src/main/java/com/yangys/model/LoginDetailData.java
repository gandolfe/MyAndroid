package com.yangys.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by yangys on 2018/9/3.
 */

public class LoginDetailData extends RealmObject{

    @Expose
    @SerializedName("collectIds")
    private RealmList<Integer> collectIds;

    @Expose
    @SerializedName("email")
    private String email;

    @Expose
    @SerializedName("icon")
    private String icon;

    @Expose
    @SerializedName("id")
    private String id;

    @Expose
    @SerializedName("password")
    private String password;

    @Expose
    @SerializedName("type")
    private String type;

    @Expose
    @SerializedName("username")
    private String username;

    public RealmList<Integer> getCollectIds() {
        return collectIds;
    }

    public void setCollectIds(RealmList<Integer> collectIds) {
        this.collectIds = collectIds;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
