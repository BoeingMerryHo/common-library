package io.github.boeingmerryho.commonlibrary.exception;

import org.springframework.http.HttpStatus;

public interface BaseErrorCode {
	HttpStatus getStatus();

	String getErrorCode();

	String getMessage();
}
