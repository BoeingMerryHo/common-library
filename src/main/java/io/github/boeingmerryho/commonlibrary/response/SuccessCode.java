package io.github.boeingmerryho.commonlibrary.response;

import org.springframework.http.HttpStatus;

public interface SuccessCode {
	HttpStatus getStatus();

	String getMessage();
}