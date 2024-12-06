package com.branch.service;

import com.branch.mapper.UserInfoMapper;
import com.branch.model.GithubRepoResponse;
import com.branch.model.GithubUserResponse;
import com.branch.model.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoService {

    private static final Logger logger = LoggerFactory.getLogger(UserInfoService.class);
    private final GithubAccessService githubAccessService;
    private final UserInfoMapper mapper;

    @Autowired
    public UserInfoService(GithubAccessService githubAccessService) {
        this.githubAccessService = githubAccessService;
        this.mapper = new UserInfoMapper();
    }

    public UserInfo getUserInfo(String userId) {
        logger.info("Starting to retrieve user info for userId: {}", userId);
        GithubUserResponse userResponse = githubAccessService.getGithubUser(userId);
        List<GithubRepoResponse> repoResponses = githubAccessService.getGithubRepos(userId);

        logger.debug("Mapping GitHub responses to UserInfo model for userId: {}", userId);
        UserInfo userInfo = mapper.toUserInfo(userResponse, repoResponses);

        logger.info("Successfully retrieved and mapped user info: {}", userInfo);
        return userInfo;
    }
}
