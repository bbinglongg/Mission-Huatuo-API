package com.hase.huatuo.healthcheck.error.advice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hase.huatuo.healthcheck.error.ApiError;
import com.hase.huatuo.healthcheck.error.ErrorInfo;
import com.hase.huatuo.healthcheck.error.ErrorMessage;
import com.hase.huatuo.healthcheck.error.exception.BadRequestRestException;
import com.hase.huatuo.healthcheck.error.exception.TechnicalRestException;

@RestControllerAdvice
public class RestResponseExceptionHandler {

	
	@ExceptionHandler(BadRequestRestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiError handleBadRequestRestdException(HttpServletRequest req, BadRequestRestException ex) {
		
		return new ApiError(HttpStatus.BAD_REQUEST, req, ex.getErrorInfo());
	}
	
	@ExceptionHandler(TechnicalRestException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ApiError handleBadRequestRestdException(HttpServletRequest req, TechnicalRestException ex) {
		
		return new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, req, ex.getErrorInfo());
	}
	
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ApiError handleUnexcepionedException(HttpServletRequest req, Exception ex) {
		
		return buildApiError(HttpStatus.INTERNAL_SERVER_ERROR, req, "HTTP_ERR", ex.getMessage());
	}
	
	private ApiError buildApiError(HttpStatus httpStatus, HttpServletRequest req, String errPrefix, String msg) {
		
		ErrorInfo errorInfo = new ErrorInfo(errPrefix);
		errorInfo.setMessage(new ErrorMessage(msg));
		
		return new ApiError(httpStatus, req, errorInfo);
	}
	
}
