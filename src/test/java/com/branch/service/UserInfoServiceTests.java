package com.branch.service;

import com.branch.mapper.UserInfoMapper;
import com.branch.model.UserInfo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserInfoServiceTests {

    @Mock
    private UserInfoService service;

    @Mock
    private UserInfoMapper mapper;

    @InjectMocks
    private GithubAccessService githubAccessService;

    @Test
    public void user_info_service_success() {
        String userId = "octocat";
        UserInfo testInfo = new UserInfo();
        testInfo.setUserName(userId);

        when(service.getUserInfo(userId)).thenReturn(testInfo);
        UserInfo userInfo = service.getUserInfo(userId);

        verify(service, times(1)).getUserInfo(userId);
        assertNotNull(userInfo);
        assertEquals(userInfo.getUserName(), testInfo.getUserName());
    }
}
