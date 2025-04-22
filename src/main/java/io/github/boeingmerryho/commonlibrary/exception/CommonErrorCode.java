package io.github.boeingmerryho.commonlibrary.exception;

import org.springframework.http.HttpStatus;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum CommonErrorCode implements BaseErrorCode{

	FORBIDDEN(HttpStatus.FORBIDDEN, "B-001", "권한이 없습니다."),
	UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "B-002", "인증되지 않은 사용자입니다."),
	USER_NOT_FOUND(HttpStatus.UNAUTHORIZED, "B-003", "사용자 정보를 찾을 수 없습니다."),
	INVALID_USER_STATUS(HttpStatus.UNAUTHORIZED, "B-004", "확인되지 않은 사용자입니다."),
	INVALID_ROLE(HttpStatus.FORBIDDEN, "B-005", "해당 작업을 수행할 수 없는 역할입니다."),
	MISSING_USER_INFO(HttpStatus.UNAUTHORIZED, "B-006", "사용자 정보가 유실되었습니다."),
	;

	private final HttpStatus status;
	private final String errorCode;
	private final String message;

	@Override
	public HttpStatus getStatus() {
		return this.status;
	}

	@Override
	public String getErrorCode() {
		return this.errorCode;
	}

	@Override
	public String getMessage() {
		return this.message;
	}
}
