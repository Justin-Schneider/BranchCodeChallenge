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

/**
 * Service class for managing user information by integrating with GitHub API and Redis cache.
 *
 * This service is responsible for retrieving user information, including their details and repositories,
 * from GitHub. It first checks the Redis cache for existing user data. If the data is not available,
 * it fetches the data from GitHub, maps it to a `UserInfo` model, and stores it in Redis for future use.
 */
@Service
public class UserInfoService {

    private static final Logger logger = LoggerFactory.getLogger(UserInfoService.class);
    private final GithubAccessService githubAccessService;
    private final RedisService redisService;
    private final UserInfoMapper mapper;

    @Autowired
    public UserInfoService(GithubAccessService githubAccessService, RedisService redisService) {
        this.githubAccessService = githubAccessService;
        this.redisService = redisService;
        this.mapper = new UserInfoMapper();
    }

    public UserInfo getUserInfo(String userId) {
        logger.info("Starting to retrieve user info for userId: {}", userId);

        UserInfo userInfo = redisService.get(userId);

        if (userInfo == null) {
            GithubUserResponse userResponse = githubAccessService.getGithubUser(userId);
            List<GithubRepoResponse> repoResponses = githubAccessService.getGithubRepos(userId);

            logger.debug("Mapping GitHub responses to UserInfo model for userId: {}", userId);
            userInfo = mapper.toUserInfo(userResponse, repoResponses);
            redisService.save(userId, userInfo);
        }

        logger.info("Successfully retrieved and mapped user info: {}", userInfo);
        return userInfo;
    }
}
