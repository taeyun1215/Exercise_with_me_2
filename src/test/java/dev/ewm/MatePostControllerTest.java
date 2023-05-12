package dev.ewm;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.ewm.configure.LocalTimeSerializer;
import dev.ewm.domain.matePost.MatePost;
import dev.ewm.domain.matePost.repo.MatePostRepo;
import dev.ewm.domain.matePost.request.MatePostCreateRequest;
import dev.ewm.domain.matePost.request.MatePostModifyRequest;
import dev.ewm.domain.user.User;
import dev.ewm.domain.user.UserRepo;
import dev.ewm.domain.user.constant.Role;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import java.time.LocalTime;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MatePostControllerTest {

    @Autowired
    MockMvc mockMvc;

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

    @AfterEach
    public void clear() {
        // 테스트용 게시글 데이터 삭제
        matePostRepo.deleteAll();
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
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.title", is("exercise with me")))
                .andExpect(jsonPath("$.data.content", is("Looking for someone to exercise")))
                .andExpect(jsonPath("$.data.gym", is("fitness center")))
                .andExpect(jsonPath("$.data.startTime", is("15:00:00")))
                .andExpect(jsonPath("$.data.endTime", is("16:00:00")))
                .andExpect(jsonPath("$.data.user.username", is("test111")))
                .andExpect(jsonPath("$.data.user.nickname", is("test")))
                .andExpect(jsonPath("$.data.user.phone", is("010-2415-6806")))
                .andExpect(jsonPath("$.data.user.email", is("test@naver.com")))
                .andExpect(jsonPath("$.data.user.role", is("USER")));
    }

    @Test
    @DisplayName("운동 매칭 게시글 보기 테스트")
    public void viewMatePostTest() throws Exception {
        // Given
        Optional<User> user = userRepo.findById(1L);

        // When
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/matePost/view/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("LOGIN_MEMBER", user.get().getUsername())
        );

        // then
        resultActions
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.title", is("exercise with me")))
                .andExpect(jsonPath("$.data.content", is("Looking for someone to exercise")))
                .andExpect(jsonPath("$.data.gym", is("fitness center")))
                .andExpect(jsonPath("$.data.view", is(1)))
                .andExpect(cookie().value("postView", "[1]"));

    }

    @Test
    @DisplayName("운동 매칭 게시글 수정 테스트")
    public void modifyMatePostTest() throws Exception {
        // Given
        MatePostModifyRequest request = new MatePostModifyRequest();
        request.setTitle("Recruitment of people to exercise");
        request.setContent("The title is");
        request.setGym("Jungnangcheon Stream");
        request.setStartTime(LocalTime.parse("09:00:00"));
        request.setEndTime(LocalTime.parse("09:30:00"));

        Optional<User> user = userRepo.findById(1L);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalTime.class, new LocalTimeSerializer());
        Gson gson = gsonBuilder.setPrettyPrinting().create();

        // When
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.put("/matePost/modify/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(request))
                        .header("LOGIN_MEMBER", user.get().getUsername())
        );

        // then
        resultActions
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.title", is("Recruitment of people to exercise")))
                .andExpect(jsonPath("$.data.content", is("The title is")))
                .andExpect(jsonPath("$.data.gym", is("Jungnangcheon Stream")));

    }

    @Test
    @DisplayName("운동 매칭 게시글 참여 테스트")
    public void joinMateTest() throws Exception {
        // Given
        User joinUser = User.builder()
                .username("devty1215")
                .password(passwordEncoder.encode("woogi101^^"))
                .nickname("test22222")
                .phone("010-1111-2222")
                .email("taeyun1215@naver.com")
                .role(Role.USER)
                .build();

        userRepo.save(joinUser);

        Optional<User> user = userRepo.findById(2L);

        // When
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/matePost/join/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("LOGIN_MEMBER", user.get().getUsername())
        );

        // then
        resultActions
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].username", is("devty1215")))
                .andExpect(jsonPath("$.data[0].type", is("PARTICIPANT")));
    }

    @Test
    @DisplayName("운동 매칭 게시글 검색(제목) 테스트")
    public void searchMatePostTitleTest() throws Exception {
        // Given
        MatePostSearchRequireRequest request = new MatePostSearchRequireRequest();
        request.setTitle("exercise");

        Optional<User> user = userRepo.findById(1L);

        // When
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/matePost/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(request))
                        .header("LOGIN_MEMBER", user.get().getUsername())
        );

        // then
        resultActions
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("운동 매칭 게시글 검색(헬스장) 테스트")
    public void searchMatePostGymTest() throws Exception {
        // Given
        MatePostSearchRequireRequest request = new MatePostSearchRequireRequest();
        request.setGym("center");

        Optional<User> user = userRepo.findById(1L);

        // When
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/matePost/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(request))
                        .header("LOGIN_MEMBER", user.get().getUsername())
        );

        // then
        resultActions
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("운동 매칭 게시글 검색(시간) 테스트")
    public void searchMatePostTimeTest() throws Exception {
        // Given
        MatePostSearchRequireRequest request = new MatePostSearchRequireRequest();
        request.setStartTime(LocalTime.parse("14:30:00"));
        request.setEndTime(LocalTime.parse("16:30:00"));

        Optional<User> user = userRepo.findById(1L);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalTime.class, new LocalTimeSerializer());
        Gson gson = gsonBuilder.setPrettyPrinting().create();

        // When
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/matePost/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(request))
                        .header("LOGIN_MEMBER", user.get().getUsername())
        );

        // then
        resultActions
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("운동 매칭 게시글 페이징 테스트")
    public void pagingMatePostTest() throws Exception {
        // Given
        Optional<User> user = userRepo.findById(1L);

        for (int i = 0; i < 30; i++) {
            MatePost matePost = MatePost.builder()
                    .title("title" + i)
                    .content("content" + i)
                    .gym("gym" + i)
                    .startTime(LocalTime.parse("15:00:00"))
                    .endTime(LocalTime.parse("16:00:00"))
                    .user(user.get())
                    .build();

            matePostRepo.save(matePost);
        }

        // When
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/matePost/all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("LOGIN_MEMBER", user.get().getUsername())
        );

        // then
        resultActions
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data", hasSize(10)))
                .andExpect(jsonPath("$.data[0].title", is("title29")));
    }
}
