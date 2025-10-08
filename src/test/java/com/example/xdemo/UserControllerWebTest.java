package com.example.xdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.xdemo.controller.UserController;
import com.example.xdemo.dto.UserDto;
import com.example.xdemo.service.UserService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerWebTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService svc; // <- mocked: no real DB

    @Test
    void register_returns_saved_user() throws Exception {
        UserDto saved = new UserDto(1L, "Pun", "pun@example.com");
        when(svc.register("Pun", "pun@example.com")).thenReturn(saved);

        String body = "{\"name\":\"Pun\",\"email\":\"pun@example.com\"}";

        mvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
           .andExpect(status().isOk())
           .andExpect(content().json("{\"id\":1,\"name\":\"Pun\",\"email\":\"pun@example.com\"}"));
    }

    @Test
    void update_name_returns_updated_user() throws Exception {
        UserDto updated = new UserDto(42L, "NewName", "neo@zion.org");
        when(svc.updateName(42L, "NewName")).thenReturn(updated);

        String body = "{\"name\":\"NewName\"}";

        mvc.perform(patch("/api/users/42/name")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
           .andExpect(status().isOk())
           .andExpect(content().json("{\"id\":42,\"name\":\"NewName\",\"email\":\"neo@zion.org\"}"));
    }


}
