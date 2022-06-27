package com.jspark.zerobase_project02.exception;

import com.jspark.zerobase_project02.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.jspark.zerobase_project02.type.ErrorCode.INTERNAL_SERVER_ERROR;
import static com.jspark.zerobase_project02.type.ErrorCode.INVALID_REQUEST;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccountException.class)
    public ErrorResponse handleAccountException(AccountException e) {
        log.error("{} is occurred.", e.getErrorCode());

        return new ErrorResponse(e.getErrorCode(), e.getErrorMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ErrorResponse handleAccountException(DataIntegrityViolationException e) {
        log.error("DataIntegrityViolationException is occurred.", e);

        return new ErrorResponse(
                INVALID_REQUEST,
                INVALID_REQUEST.getDescription());
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponse handleException(Exception e) {
        log.error("Exception is occurred.", e);

        return new ErrorResponse(
                INTERNAL_SERVER_ERROR,
                INTERNAL_SERVER_ERROR.getDescription());
    }
}
