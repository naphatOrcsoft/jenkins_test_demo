package com.example.xdemo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.xdemo.dto.UserDto;
import com.example.xdemo.repository.UserRepository;
import com.example.xdemo.service.UserService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceGetAlteredEmailTest {

    @Mock
    private UserRepository repo;

    @InjectMocks
    private UserService svc;

    @Test
    void getAlteredEmail_finds_user_by_name_and_transforms_email() {
        // given a DB record: name=mrtest, email=test@gmail.com
        UserDto entity = new UserDto(1L, "mrtest", "test@gmail.com");
        when(repo.findByName("mrtest")).thenReturn(Optional.of(entity));

        // when
        String result = svc.getAlteredEmail("mrtest");

        // then
        assertEquals("test:mail", result);
        verify(repo, times(1)).findByName("mrtest");
        verifyNoMoreInteractions(repo);
    }

    @Test
    void getAlteredEmail_user_not_found_throws() {
        when(repo.findByName("unknown")).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> svc.getAlteredEmail("unknown"));
        assertTrue(ex.getMessage().toLowerCase().contains("user not found"));
    }

    @Test
    void getAlteredEmail_invalid_email_throws() {
        UserDto bad = new UserDto(2L, "bad", "not-an-email");
        when(repo.findByName("bad")).thenReturn(Optional.of(bad));

        assertThrows(IllegalArgumentException.class, () -> svc.getAlteredEmail("bad"));
    }
}
