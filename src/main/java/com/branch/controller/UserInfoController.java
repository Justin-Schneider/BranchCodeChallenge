package com.branch.controller;

import com.branch.model.UserInfo;
import com.branch.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserInfoController {

    private static final Logger logger = LoggerFactory.getLogger(UserInfoController.class);
    private final UserInfoService userInfoService;

    @Autowired
    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @GetMapping("/user-information/{userId}")
    public UserInfo getUserInfo(@PathVariable String userId) {
        logger.info("Received request for /user-information/{}", userId);
        return userInfoService.getUserInfo(userId);
    }
}
