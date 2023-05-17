package dev.ewm.global.argumentResolver;

import dev.ewm.user.adapter.out.persistence.UserJpaEntity;
import dev.ewm.user.adapter.out.persistence.UserJpaRepo;
import dev.ewm.global.annotation.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.persistence.EntityNotFoundException;

@Component
@RequiredArgsConstructor
public class LoginArgumentResolver implements HandlerMethodArgumentResolver {

    private static final String LOGIN_MEMBER = "LOGIN_MEMBER";
    private final UserJpaRepo userJpaRepo;

    @Override
    public boolean supportsParameter(final MethodParameter parameter) {
        return parameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(
            final MethodParameter parameter,
            final ModelAndViewContainer mavContainer,
            final NativeWebRequest webRequest,
            final WebDataBinderFactory binderFactory
    ) {
        final String username = webRequest.getHeader(LOGIN_MEMBER);
        UserJpaEntity userJpaEntity = userJpaRepo.findByUsername(username).orElseThrow(
                () -> new EntityNotFoundException()
        );

        return userJpaEntity;
    }
}
