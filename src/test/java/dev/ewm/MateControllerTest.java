package dev.ewm;

import com.google.gson.Gson;
import dev.ewm.domain.user.User;
import dev.ewm.domain.user.UserRepo;
import dev.ewm.domain.user.constant.Role;
import dev.ewm.domain.user.request.UserLoginRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class MateControllerTest {

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    public void init() {
    }

    @Test
    @DisplayName("로그인 테스트")
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
