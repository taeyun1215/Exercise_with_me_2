package dev.ewm;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import dev.ewm.user.adapter.in.request.LoginUserRequest;
import dev.ewm.user.adapter.in.request.RegisterUserRequest;
import dev.ewm.user.adapter.out.persistence.UserJpaEntity;
import dev.ewm.user.adapter.out.persistence.UserJpaRepo;
import dev.ewm.user.application.port.in.usecase.RegisterUserUseCase;
import dev.ewm.user.domain.User;
import dev.ewm.user.domain.constant.Role;
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
    UserJpaRepo userJpaRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @BeforeEach
    public void init() {
        UserJpaEntity user = UserJpaEntity.builder()
                .username("test111")
                .password(passwordEncoder.encode("비밀번호486!"))
                .nickname("test")
                .phone("010-2415-6806")
                .email("test@naver.com")
                .role(Role.USER)
                .build();

        userJpaRepo.save(user);
    }

    @AfterEach
    public void clear() {
        // 테스트용 게시글 데이터 삭제
        userJpaRepo.deleteAll();
    }

    @Test // @Test : 테스트가 수행되는 메소드를 가르킨다.
    @DisplayName("회원가입 성공 테스트")
    public void registerUserTest() throws Exception {
        // Given
        RegisterUserRequest request = new RegisterUserRequest(
                "devty1215",
                "woogi101^^",
                "woogi101^^",
                "test22222",
                "010-1111-2222",
                "taeyun1215@naver.com"
        );

        // When
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request))
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
        LoginUserRequest request = new LoginUserRequest(
                "test111",
                "비밀번호486!"
        );

        // When
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request))
        );

        // then
        resultActions
                .andDo(print())
                .andExpect(status().isOk());
    }
}
