package com.branch.service;

import com.branch.model.GithubRepoResponse;
import com.branch.model.GithubUserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class GithubAccessService {

    private static final Logger logger = LoggerFactory.getLogger(GithubAccessService.class);
    private static final RestTemplate template = new RestTemplate();
    private final String url;

    @Autowired
    public GithubAccessService(@Value("${branch-github-url}") String url) {
        this.url = url;
    }

    public GithubUserResponse getGithubUser(String userId) {
        String userUrl = url + "users/" + userId;
        logger.info("Requesting Github for User Information: {}", userUrl);
        ResponseEntity<GithubUserResponse> response = template.exchange(
                userUrl,
                HttpMethod.GET,
                null,
                GithubUserResponse.class
        );
        logger.info("Response from Github for User Information: {}", response);
        return response.getBody();
    }

    public List<GithubRepoResponse> getGithubRepos(String userId) {
        String repoUrl = url + "users/" + userId + "/repos";
        logger.info("Requesting Github for User Repository Information: {}", repoUrl);
        ResponseEntity<List<GithubRepoResponse>> response = template.exchange(
                repoUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<GithubRepoResponse>>() {
                }
        );
        logger.info("Response from Github for User Repository Information: {}", response);
        return response.getBody();
    }
}
