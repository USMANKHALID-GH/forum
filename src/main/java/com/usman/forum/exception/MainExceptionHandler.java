package com.usman.forum.exception;

import com.usman.forum.dto.BaseResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
@Slf4j
public class MainExceptionHandler {

    @ExceptionHandler(value = {BusinessException.class})
    public final ResponseEntity<BaseResponseDto> handleException(BusinessException e) {
        return createResponse(e.getHttpStatus(), e.getMessage(), e);
    }

    @ExceptionHandler(value = {Exception.class})
    public final ResponseEntity<BaseResponseDto> handleException(Exception e) {
        return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Sistemsel bir hata alınmıştır, Daha sonra tekrar deneyiniz.", e);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST,code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> GeneralExeption(MethodArgumentNotValidException ex){
        Map<String ,String> map= new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(fieldError -> map.put(fieldError.getField(),fieldError.getDefaultMessage()));

        return map;

    }

    private final ResponseEntity<BaseResponseDto> createResponse(HttpStatus httpStatus, String message, Exception e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(httpStatus).body(BaseResponseDto.builder().
                statusCode(httpStatus.value()).status(httpStatus.name()).message(message).success(false).build());
    }
}
