package io.github.boeingmerryho.commonlibrary.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {

	private final String errorCode;
	private final String message;

	static ErrorResponse of(String errorCode, String message) {
		return new ErrorResponse(errorCode, message);
	}
}