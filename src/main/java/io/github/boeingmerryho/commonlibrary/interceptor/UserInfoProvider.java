package io.github.boeingmerryho.commonlibrary.interceptor;

import java.util.Map;

public interface UserInfoProvider {
	Map<String, Object> getUserInfo(long userId);
}
