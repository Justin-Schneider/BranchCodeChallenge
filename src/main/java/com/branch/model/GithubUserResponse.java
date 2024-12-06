package com.branch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties
public class GithubUserResponse {

    @JsonProperty("login")
    private String login;

    @JsonProperty("name")
    private String name;

    @JsonProperty("avatar_url")
    private String avatarUrl;

    @JsonProperty("location")
    private String location;

    @JsonProperty("email")
    private String email;

    @JsonProperty("html_url")
    private String htmlUrl;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;
}
