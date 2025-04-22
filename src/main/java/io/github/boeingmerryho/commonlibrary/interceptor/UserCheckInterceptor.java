package io.github.boeingmerryho.commonlibrary.interceptor;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.boeingmerryho.commonlibrary.entity.UserRoleType;
import io.github.boeingmerryho.commonlibrary.exception.CommonErrorCode;
import io.github.boeingmerryho.commonlibrary.exception.ErrorResponse;
import io.github.boeingmerryho.commonlibrary.exception.GlobalException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserCheckInterceptor implements HandlerInterceptor {

	private final UserInfoProvider userInfoProvider;
	private final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
		Exception {

		try {
			String userIdAttr = request.getHeader("X-User-Id");
			if (userIdAttr == null) {
				throw new GlobalException(CommonErrorCode.UNAUTHORIZED);
			}

			long userId = Long.parseLong(userIdAttr);
			Map<String, Object> userInfo = userInfoProvider.getUserInfo(userId);

			validateUserInfo(userInfo);
			injectUserAttributes(request, userId, userInfo);

			if (handler instanceof HandlerMethod handlerMethod) {
				RequiredRoles requiredRoles = handlerMethod.getMethodAnnotation(RequiredRoles.class);
				if (requiredRoles != null) {
					boolean hasPermission = Arrays.stream(requiredRoles.value())
						.anyMatch(role -> role.equals(UserRoleType.valueOf((String) userInfo.get("role"))));
					if (!hasPermission) {
						throw new GlobalException(CommonErrorCode.FORBIDDEN);
					}
				}
			}

			return true;
		} catch (GlobalException e) {
			handleException(response, e);
			return false;
		}
	}

	private void validateUserInfo(Map<String, Object> userInfo) {
		if (userInfo == null || userInfo.isEmpty()) {
			throw new GlobalException(CommonErrorCode.USER_NOT_FOUND);
		}

		final String[] requiredKeys = {"email", "username", "nickname", "birth", "role"};
		for (String key : requiredKeys) {
			if (!userInfo.containsKey(key)) {
				throw new GlobalException(CommonErrorCode.MISSING_USER_INFO);
			}
		}
	}

	private void injectUserAttributes(HttpServletRequest request, long userId, Map<String, Object> userInfo) {
		request.setAttribute("userId", userId);
		request.setAttribute("username", userInfo.get("username"));
		request.setAttribute("email", userInfo.get("email"));
		request.setAttribute("nickname", userInfo.get("nickname"));
		request.setAttribute("birth", userInfo.get("birth"));
		request.setAttribute("role", userInfo.get("role"));
	}

	private void handleException(HttpServletResponse response, GlobalException ex) throws IOException {
		response.setStatus(ex.getErrorCode().getStatus().value());
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		ErrorResponse errorResponse = ErrorResponse.of(
			ex.getErrorCode().getErrorCode(),
			ex.getErrorCode().getMessage()
		);
		response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
	}
}
