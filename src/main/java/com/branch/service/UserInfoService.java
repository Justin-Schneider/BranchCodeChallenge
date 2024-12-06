package com.branch.service;

import com.branch.model.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {

    private static final Logger logger = LoggerFactory.getLogger(UserInfoService.class);

    public UserInfo getUserInfo(String userId) {
        return new UserInfo();
    }
}
