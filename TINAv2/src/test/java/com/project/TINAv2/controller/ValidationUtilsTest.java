package com.project.controller;

import com.project.services.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
class ValidationUtilsTest {

    @Mock
    private UserService userService;

    @Test
    void validateUsername() {

        ValidationUtils validationUtils = new ValidationUtils();

        Assert.assertTrue(validationUtils.validateUsername("aA5.6A"));

    }
}