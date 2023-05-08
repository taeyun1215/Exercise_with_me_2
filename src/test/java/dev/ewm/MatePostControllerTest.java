package dev.ewm;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.ewm.configure.LocalTimeSerializer;
import dev.ewm.domain.matePost.MatePost;
import dev.ewm.domain.matePost.repo.MatePostRepo;
import dev.ewm.domain.matePost.request.MatePostCreateRequest;
import dev.ewm.domain.user.User;
import dev.ewm.domain.user.UserRepo;
import dev.ewm.domain.user.constant.Role;
import dev.ewm.domain.user.request.UserLoginRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MatePostControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    protected MockHttpSession session;

    @Autowired
    MatePostRepo matePostRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @BeforeEach
    public void init() {
        User user = User.builder()
                .username("test111")
                .password(passwordEncoder.encode("비밀번호486!"))
                .nickname("test")
                .phone("010-2415-6806")
                .email("test@naver.com")
                .role(Role.USER)
                .build();

        userRepo.save(user);

        MatePost matePost = MatePost.builder()
                .title("exercise with me")
                .content("Looking for someone to exercise")
                .gym("fitness center")
                .startTime(LocalTime.parse("15:00:00"))
                .endTime(LocalTime.parse("16:00:00"))
                .user(user)
                .build();

        matePostRepo.save(matePost);
    }

    @Test
    @DisplayName("운동 매칭 게시글 생성 테스트")
    public void createMatePostTest() throws Exception {
        // Given
        MatePostCreateRequest request = new MatePostCreateRequest();
        request.setTitle("exercise with me");
        request.setContent("Looking for someone to exercise");
        request.setGym("fitness center");
        request.setStartTime(LocalTime.parse("15:00:00"));
        request.setEndTime(LocalTime.parse("16:00:00"));

        Optional<User> user = userRepo.findById(1L);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalTime.class, new LocalTimeSerializer());
        Gson gson = gsonBuilder.setPrettyPrinting().create();


        // When
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/matePost/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(request))
                        .header("LOGIN_MEMBER", user.get().getUsername())
        );

        // then
        resultActions
                .andDo(print())
                .andExpect(status().isOk());
    }

}
