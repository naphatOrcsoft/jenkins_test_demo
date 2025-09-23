package com.example.xdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.xdemo.controller.GreetController;
import com.example.xdemo.service.GreetService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GreetController.class)
class GreetControllerWebTest {
  @Autowired MockMvc mvc;
  @MockBean GreetService svc;

  @Test void greet_endpoint() throws Exception {
    when(svc.greet("Trinity")).thenReturn("Hello, Trinity!");
    mvc.perform(get("/api/greet").param("name","Trinity"))
       .andExpect(status().isOk())
       .andExpect(content().string("Hello, Trinity!"));
  }
}

