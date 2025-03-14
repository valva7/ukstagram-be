package org.ukstagram.global.pricipal;

import lombok.AllArgsConstructor;
import org.ukstagram.domain.auth.model.CustomUserDetails;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.ukstagram.domain.user.model.entity.MemberEntity;
import org.ukstagram.domain.user.repository.MemberRepository;

// HandlerMethodArgumentResolver -> Spring MVC에서 컨트롤러 메서드의 파라미터를 동적으로 해결하기 위해 사용되는 인터페이스
@AllArgsConstructor
public class AuthenticationPrincipalArgumentResolver implements HandlerMethodArgumentResolver {

    private final MemberRepository memberRepository;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthPrincipal.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

            MemberEntity member = memberRepository.findById(userDetails.getUserId()).orElseThrow();
            // 현재 로그인한 사용자 정보를 반환
            return new UserAuth(member.getId(), member.getNickname()); // 예시로 사용자 정보를 객체로 반환
        }

        return null;
    }
}