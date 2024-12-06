package com.branch.mapper;

import com.branch.model.GithubRepoResponse;
import com.branch.model.GithubUserResponse;
import com.branch.model.Repo;
import com.branch.model.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * A utility class that maps data from GitHub API responses to the internal model objects.
 *
 * This class is responsible for converting GitHub-specific data types, such as {@link GithubUserResponse}
 * and {@link GithubRepoResponse}, into the corresponding internal models used in the application,
 * such as {@link UserInfo} and {@link Repo}.
 */
public class UserInfoMapper {

    public UserInfo toUserInfo(GithubUserResponse user, List<GithubRepoResponse> repoResponses) {
        UserInfo info = new UserInfo();
        info.setUserName(user.getLogin());
        info.setDisplayName(user.getName());
        info.setAvatar(user.getAvatarUrl());
        info.setGeoLocation(user.getLocation());
        info.setEmail(user.getEmail());
        info.setUrl(user.getHtmlUrl());
        info.setCreatedAt(user.getCreatedAt());
        info.setRepos(toRepo(repoResponses));
        return info;
    }

    public List<Repo> toRepo(List<GithubRepoResponse> repoResponses) {
        List<Repo> repos = new ArrayList<>();
        for (GithubRepoResponse repoResponse : repoResponses) {
            Repo repo = new Repo();
            repo.setName(repoResponse.getName());
            repo.setUrl(repoResponse.getUrl());
            repos.add(repo);
        }
        return repos;
    }
}
