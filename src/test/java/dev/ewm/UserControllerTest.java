package dev.ewm;

import com.google.gson.Gson;
import dev.ewm.user.domain.User;
import dev.ewm.domain.user.UserRepo;
import dev.ewm.user.domain.constant.Role;
import dev.ewm.domain.user.request.UserLoginRequest;
import dev.ewm.domain.user.request.UserRegisterRequest;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @BeforeEach
    public void init() {
        User user = User.builder()
                .id(1L)
                .username("test111")
                .password(passwordEncoder.encode("비밀번호486!"))
                .nickname("test")
                .phone("010-2415-6806")
                .email("test@naver.com")
                .role(Role.USER)
                .build();

        userRepo.save(user);
    }

    @Test // @Test : 테스트가 수행되는 메소드를 가르킨다.
    @DisplayName("회원가입 성공 테스트")
    public void registerUserTest() throws Exception {
        // Given
        UserRegisterRequest request = new UserRegisterRequest();
        request.setUsername("devty1215");
        request.setPassword("woogi101^^");
        request.setConfirmPassword("woogi101^^");
        request.setNickname("test22222");
        request.setPhone("010-1111-2222");
        request.setEmail("taeyun1215@naver.com");

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
                .andExpect(jsonPath("$.data.username", is("devty1215")))
                .andExpect(jsonPath("$.data.nickname", is("test22222")))
                .andExpect(jsonPath("$.data.phone", is("010-1111-2222")))
                .andExpect(jsonPath("$.data.email", is("taeyun1215@naver.com")));
    }

    @Test
    @DisplayName("username 중복 테스트")
    public void checkUsernameTest() throws Exception {
        // Given
        String username = "taeyun1215";

        // When
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/users/username/"+username+"/exists")
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("nickname 중복 테스트")
    public void checkNicknameTest() throws Exception {
        // Given
        String nickname = "운동하자";

        // When
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/users/nickname/"+nickname+"/exists")
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("로그인 성공 테스트")
    public void loginUserTest() throws Exception {
        // Given
        UserLoginRequest request = new UserLoginRequest();
        request.setUsername("test111");
        request.setPassword("비밀번호486!");

        // When
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(request))
        );

        // then
        resultActions
                .andDo(print())
                .andExpect(status().isOk());
    }
}
