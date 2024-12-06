package com.branch.service;

import com.branch.model.UserInfo;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
public class RedisServiceTests {

    @Mock
    private RedisService service;

    @Test
    public void redis_service_get_successful() {
        String userId = "Jusitn";
        UserInfo testInfo = new UserInfo();
        testInfo.setUserName("Justin");

        when(service.get(userId)).thenReturn(testInfo);
        UserInfo userInfo = service.get(userId);

        assertNotNull(userInfo);
        assertEquals(userInfo.getUserName(), testInfo.getUserName());
    }

    @Test
    public void redis_service_save_successful() {
        String userId = "Jusitn";
        UserInfo testInfo = new UserInfo();
        testInfo.setUserName("Justin");

        service.save(userId, testInfo);

        verify(service, times(1)).save(anyString(), eq(testInfo));
    }
}
