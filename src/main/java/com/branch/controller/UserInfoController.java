package com.branch.controller;

import com.branch.model.UserInfo;
import com.branch.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.regex.Pattern;

/**
 * Controller class that handles HTTP requests related to user information.
 *
 * This class defines a RESTful endpoint to retrieve user information from GitHub based on a given userId.
 * It validates the provided userId to ensure it conforms to GitHub's username format and calls the
 * {@link UserInfoService} to retrieve and return the user details. If the username is invalid, a 400 Bad Request
 * response is returned with an appropriate error message.
 */
@RestController
public class UserInfoController {

    private static final String BAD_USERNAME_EXCEPTION_MESSAGE = "That isn't a properly formatted github username, they can only be 39 character longs and only contain alphanumeric characters and hyphens";
    //Checks that all characters are alphanumeric or a -
    private static final String BAD_USERNAME_REGEX = "^[a-zA-Z0-9-]+$";
    private static final Logger logger = LoggerFactory.getLogger(UserInfoController.class);
    private final UserInfoService userInfoService;

    @Autowired
    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @GetMapping("/user-information/{userId}")
    public UserInfo getUserInfo(@PathVariable String userId) {
        logger.info("Received request for /user-information/{}", userId);

        if (!Pattern.matches(BAD_USERNAME_REGEX, userId) || userId.length() > 40) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, BAD_USERNAME_EXCEPTION_MESSAGE);
        }

        return userInfoService.getUserInfo(userId);
    }
}
