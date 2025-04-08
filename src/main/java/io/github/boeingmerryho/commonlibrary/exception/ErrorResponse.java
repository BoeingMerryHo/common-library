package io.github.boeingmerryho.commonlibrary.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {

	private final String errorCode;
	private final String message;

	public static ErrorResponse of(String errorCode, String message) {
		return new ErrorResponse(errorCode, message);
	}
}