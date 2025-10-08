package com.example.xdemo;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.xdemo.controller.UserController;
import com.example.xdemo.service.UserService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerAlteredEmailWebTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService svc; // mocked: tests won't hit a DB

    @Test
    void altered_email_endpoint_returns_value() throws Exception {
        when(svc.getAlteredEmail("mrtest")).thenReturn("test:mail");

        mvc.perform(get("/api/users/mrtest/altered-email"))
           .andExpect(status().isOk())
           .andExpect(content().string("test:mail"));
    }
}

