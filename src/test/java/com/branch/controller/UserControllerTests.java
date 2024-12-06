package com.branch.controller;

import com.branch.model.UserInfo;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserControllerTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void get_user_info_success() {
        UserInfo response = this.restTemplate
                .getForObject("http://localhost:" + port + "/user-information/octocat", UserInfo.class);

        assertThat(response).isNotNull();

        assertThat(response.getUserName()).isEqualTo("octocat");
        assertThat(response.getDisplayName()).isEqualTo("The Octocat");
        assertThat(response.getGeoLocation()).isEqualTo("San Francisco");
        assertThat(response.getUrl()).isEqualTo("https://github.com/octocat");
    }

}
