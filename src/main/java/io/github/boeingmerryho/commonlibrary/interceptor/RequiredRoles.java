package io.github.boeingmerryho.commonlibrary.interceptor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.github.boeingmerryho.commonlibrary.entity.UserRoleType;

@Target({ElementType.METHOD})  // 메서드에 적용
@Retention(RetentionPolicy.RUNTIME) // 런타임 시 반영
@Documented
public @interface RequiredRoles {
	UserRoleType[] value(); // 필요한 역할 목록
}