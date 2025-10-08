// package com.example.xdemo;


// import org.junit.jupiter.api.Test;
// import org.mockito.ArgumentCaptor;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;

// import com.example.xdemo.dto.UserDto;
// import com.example.xdemo.repository.UserRepository;
// import com.example.xdemo.service.UserService;

// import static org.mockito.ArgumentMatchers.*;
// import static org.mockito.Mockito.*;
// import static org.junit.jupiter.api.Assertions.*;

// class UserServiceTest {

//     @Mock
//     private UserRepository repo;

//     @InjectMocks
//     private UserService svc;

//     @Test
//     void register_saves_user_via_repo() {
//         UserRepository repo = mock(UserRepository.class);
//         UserDto saved = new UserDto(1L, "Pun", "pun@example.com");
//         when(repo.save(any(UserDto.class))).thenReturn(saved);

//         UserService svc = new UserService();
//         // manually inject mock repo since we’re not in Spring context
//         svc.getClass().getDeclaredFields()[0].setAccessible(true); // normally you'd use reflection or setter
//         try {
//             svc.getClass().getDeclaredField("repo").setAccessible(true);
//             svc.getClass().getDeclaredField("repo").set(svc, repo);
//         } catch (Exception ignored) {}

//         UserDto result = svc.register("Pun", "pun@example.com");

//         ArgumentCaptor<UserDto> captor = ArgumentCaptor.forClass(UserDto.class);
//         verify(repo).save(captor.capture());
//         assertEquals("Pun", captor.getValue().getName());
//         assertEquals(saved.getId(), result.getId());
//     }
// }

package com.example.xdemo;

import com.example.xdemo.dto.UserDto;
import com.example.xdemo.repository.UserRepository;
import com.example.xdemo.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)   // <-- initializes @Mock and @InjectMocks
class UserServiceTest {

    @Mock
    private UserRepository repo;      // Mockito creates the mock

    @InjectMocks
    private UserService svc;          // Mockito injects 'repo' into this service

    @Test
    void register_saves_user_via_repo() {
        // arrange
        UserDto saved = new UserDto(1L, "Pun", "pun@example.com");
        when(repo.save(any(UserDto.class))).thenReturn(saved);

        // act
        UserDto result = svc.register("Pun", "pun@example.com");

        // assert
        verify(repo).save(any(UserDto.class));
        assertEquals(1L, result.getId().longValue());
        assertEquals("Pun", result.getName());
        assertEquals("pun@example.com", result.getEmail());
    }
}
