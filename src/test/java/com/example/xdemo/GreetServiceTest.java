package com.example.xdemo;

import org.junit.jupiter.api.Test;

import com.example.xdemo.service.GreetService;

import static org.junit.jupiter.api.Assertions.*;

class GreetServiceTest {
  @Test void greet_default(){ assertEquals("Hello, world!", new GreetService().greet(null)); }
  @Test void greet_name(){ assertEquals("Hello, Neo!", new GreetService().greet("Neo")); }
}
