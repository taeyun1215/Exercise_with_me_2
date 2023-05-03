package dev.ewm.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import dev.ewm.domain.user.User;
import dev.ewm.domain.user.UserController;
import dev.ewm.domain.user.UserService;
import dev.ewm.domain.user.constant.Role;
import dev.ewm.domain.user.request.UserRegisterRequest;
import dev.ewm.global.argumentResolver.LoginArgumentResolver;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test // @Test : 테스트가 수행되는 메소드를 가르킨다.
    @DisplayName("회원가입 성공 테스트")
    public void registerUserTest() throws Exception {
        // Given
        UserRegisterRequest request = new UserRegisterRequest();
        request.setUsername("devty1215");
        request.setPassword("woogi101^^");
        request.setConfirmPassword("woogi101^^");
        request.setNickname("test22222");
        request.setPhone("010-2415-6806");
        request.setEmail("test11111@naver.com");

        // When
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(request))
        );

        // then
        resultActions
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.username", is("devty1215")));
    }

    @Test
    @DisplayName("username 중복 테스트")
    public void checkUsernameTest() throws Exception {
        // Given
        User user = new User();
        String username = "taeyun1215";
//        given(userService.checkUsername(username)).willReturn();

        // When
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/users/username/"+username+"/exists")
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions
                .andExpect(status().isOk());
    }
}
