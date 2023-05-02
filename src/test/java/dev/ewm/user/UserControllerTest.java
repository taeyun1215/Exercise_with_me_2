package dev.ewm.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import dev.ewm.domain.user.User;
import dev.ewm.domain.user.UserController;
import dev.ewm.domain.user.UserService;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/* @RunWith : JUnit 프레임워크가 테스트를 실행할 시 테스트 실행방법을 확장할 때 쓰는 어노테이션
 * @WebMvcTest : MVC를 위한 테스트, 컨트롤러가 예상대로 동작하는지 테스트하는데 사용됩니다. Web과 관련된 다음 어노테이션만 스캔합니다.
        (@Controller, @ControllerAdvice, @JsonComponent, Converter, GenericConverter, Filter, HandlerInterceptor, WebMvcConfigurer, HandlerMethodArgumentResolver)
 * @AutoConfigureMockMvc : @WebMvcTest와 비슷하지만 가장 큰 차이점은 컨트롤러 뿐만 아니라 테스트 대상이 아닌 @Service나 @Repository가 붙은 객체들도 모두 메모리에 올립니다.
 * @Transactional : 선언적 트랜잭션을 지원하는 어노테이션입니다. 테스트환경에서의 @Transactional은 메소드가 종료될 때 자동으로 롤백됩니다.
 * */
//@WebMvcTest(UserController.class)
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test // @Test : 테스트가 수행되는 메소드를 가르킨다.
    @DisplayName("회원가입 성공 테스트 상태값 201을 반환한다.")
    public void joinUserSuccessTest() throws Exception {
        // Given
        User user = new User();

        UserRegisterRequest request = new UserRegisterRequest();
        request.setUsername("test11111");
        request.setPassword("woogi101^^");
        request.setConfirmPassword("woogi101^^");
        request.setNickname("test22222");
        request.setPhone("010-2415-6806");
        request.setEmail("test11111@naver.com");

        // When
        when(userService.registerUser(any(UserRegisterRequest.class))).thenReturn(user);
        // doReturn(user).when(userService).registerUser(any(UserRegisterRequest.class));
        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(request))
        );

        // then
        resultActions
                .andExpect(status().isOk());
    }
}
