package io.github.boeingmerryho.commonlibrary.exception;

import lombok.Getter;

@Getter
public class GlobalException extends RuntimeException {

	private final BaseErrorCode errorCode;

	public GlobalException(BaseErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}
}
