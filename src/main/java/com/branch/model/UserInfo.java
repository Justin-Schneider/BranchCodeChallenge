package com.branch.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("display_name")
    private String displayName;

    @JsonProperty("avatar")
    private String avatar;

    @JsonProperty("geo_location")
    private String geoLocation;

    @JsonProperty("email")
    private String email;

    @JsonProperty("url")
    private String url;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("repos")
    private List<Repo> repos;
}
