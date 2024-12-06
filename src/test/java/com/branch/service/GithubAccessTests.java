package com.branch.service;

import com.branch.model.GithubRepoResponse;
import com.branch.model.GithubUserResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class GithubAccessTests {

    @Mock
    private GithubAccessService githubAccessService;

    @Test
    public void github_access_service_get_user_success(){
        String userId = "something";
        ReflectionTestUtils.setField(githubAccessService, "url", "https://api.github.com/");

        GithubUserResponse testResponse = new GithubUserResponse();
        testResponse.setLogin("login");

        when(githubAccessService.getGithubUser(userId)).thenReturn(testResponse);
        GithubUserResponse userResponse = githubAccessService.getGithubUser(userId);

        verify(githubAccessService, times(1)).getGithubUser(userId);
        assertNotNull(userResponse);
        assertEquals(userResponse.getLogin(), testResponse.getLogin());
    }

    @Test
    public void github_access_service_get_user_repos_success(){
        String userId = "something";
        ReflectionTestUtils.setField(githubAccessService, "url", "https://api.github.com/");

        List<GithubRepoResponse> testResponses = new ArrayList<>();
        GithubRepoResponse testResponse = new GithubRepoResponse();
        testResponse.setName("name");
        testResponses.add(testResponse);

        when(githubAccessService.getGithubRepos(userId)).thenReturn(testResponses);
        List<GithubRepoResponse> githubRepoResponses = githubAccessService.getGithubRepos(userId);

        verify(githubAccessService, times(1)).getGithubRepos(userId);
        assertNotNull(githubRepoResponses);
        assertEquals(githubRepoResponses.get(0).getName(), testResponses.get(0).getName());
    }

    @Test
    public void github_access_service_get_user_exception(){
        String userId = "invalidUser";
        ReflectionTestUtils.setField(githubAccessService, "url", "https://api.github.com/");

        when(githubAccessService.getGithubUser(userId)).thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND, "Not Found"));
        HttpClientErrorException thrown = assertThrows(HttpClientErrorException.class, () -> {
            githubAccessService.getGithubUser(userId);
        });

        assertEquals("404 Not Found", thrown.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, thrown.getStatusCode());

        verify(githubAccessService, times(1)).getGithubUser(userId);
    }

    @Test
    public void github_access_service_get_user_repos_exception(){
        String userId = "invalidUser";
        ReflectionTestUtils.setField(githubAccessService, "url", "https://api.github.com/");

        when(githubAccessService.getGithubRepos(userId)).thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND, "Not Found"));
        HttpClientErrorException thrown = assertThrows(HttpClientErrorException.class, () -> {
            githubAccessService.getGithubRepos(userId);
        });

        assertEquals("404 Not Found", thrown.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, thrown.getStatusCode());

        verify(githubAccessService, times(1)).getGithubRepos(userId);
    }
}
