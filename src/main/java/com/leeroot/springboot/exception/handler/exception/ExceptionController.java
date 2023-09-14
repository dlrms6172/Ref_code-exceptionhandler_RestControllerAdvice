package com.leeroot.springboot.exception.handler.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.StringJoiner;

@RestControllerAdvice // 전역 컨트롤러 예외 처리 어노테이션
public class ExceptionController {
    LinkedHashMap body;


    // REQUEST_BODY_MISSING_ERROR
    // [POST] 필수 파라미터 누락
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity MethodArgumentNotValidException(MethodArgumentNotValidException e) {
        body = new LinkedHashMap();

        // 누락된 파라미터 정보 얻기
        BindingResult bindingResult = e.getBindingResult();
        StringJoiner stringJoiner = new StringJoiner(",");

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            stringJoiner.add(fieldError.getField());
        }

        body.put("code", ValidationCode.REQUEST_BODY_MISSING_ERROR.getCode());
        body.put("message", ValidationCode.REQUEST_BODY_MISSING_ERROR.getMessage() + stringJoiner);
        body.put("description",ValidationCode.REQUEST_BODY_MISSING_ERROR.getDescription());


        return ResponseEntity.status(ValidationCode.REQUEST_BODY_MISSING_ERROR.getCode()).body(body);
    }

    // REQUEST_BODY_ERROR
    // [POST] 바디 파라미터 확인
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity HttpMessageNotReadableException(HttpMessageNotReadableException e) {
        body = new LinkedHashMap();

        String message = e.getMessage();

        int idx = message.indexOf("[\"");

        String parameter = message.substring(idx);
        // 특수문자 제거
        String match = "[^\uAC00-\uD7A30-9a-zA-Z]";

        parameter = parameter.replaceAll(match,"");


        body.put("code", ValidationCode.REQUEST_BODY_ERROR.getCode());
        body.put("message", ValidationCode.REQUEST_BODY_ERROR.getMessage() + parameter);
        body.put("description",ValidationCode.REQUEST_BODY_ERROR.getDescription());

        return ResponseEntity.status(ValidationCode.REQUEST_BODY_ERROR.getCode()).body(body);
    }

    // REQUEST_PARAM_ERROR
    // [GET] 필수 파라미터 누락 및 파라미터 확인
    @ExceptionHandler(value =  BindException.class)
    public ResponseEntity BindException(BindException e){
        body = new LinkedHashMap();

        BindingResult bindingResult = e.getBindingResult();
        StringJoiner stringJoiner = new StringJoiner(",");

        for(FieldError fieldError : bindingResult.getFieldErrors()){
            stringJoiner.add(fieldError.getField());
        }

        body.put("code", ValidationCode.REQUEST_PARAM_ERROR.getCode());
        body.put("message", ValidationCode.REQUEST_PARAM_ERROR.getMessage() + stringJoiner);
        body.put("description",ValidationCode.REQUEST_PARAM_ERROR.getDescription());


        //어떤 에러인지 알아내기
        //e.printStackTrace();

        return ResponseEntity.status(ValidationCode.REQUEST_PARAM_ERROR.getCode()).body(body);
    }

    // NOT_FOUND_URL_ERROR
    // 요청 주소 확인
    @ExceptionHandler(value = NoHandlerFoundException.class)
    public ResponseEntity NoHandlerFoundException(NoHandlerFoundException e){
        body = new LinkedHashMap();

        body.put("code", ValidationCode.NOT_FOUND_URL_ERROR.getCode());
        body.put("message", ValidationCode.NOT_FOUND_URL_ERROR.getMessage());
        body.put("description",ValidationCode.NOT_FOUND_URL_ERROR.getDescription());

        return ResponseEntity.status(ValidationCode.NOT_FOUND_URL_ERROR.getCode()).body(body);
    }

    // NOT_ALLOW_METHOD_ERROR
    // 요청 메소드 확인
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e){
        body = new LinkedHashMap();

        body.put("code", ValidationCode.NOT_ALLOW_METHOD_ERROR.getCode());
        body.put("message", ValidationCode.NOT_ALLOW_METHOD_ERROR.getMessage() + e.getMethod() + "->" + e.getSupportedHttpMethods());
        body.put("description",ValidationCode.NOT_ALLOW_METHOD_ERROR.getDescription());

        return ResponseEntity.status(ValidationCode.NOT_ALLOW_METHOD_ERROR.getCode()).body(body);
    }

    // SQL_ERROR
    // 요청 파라미터 확인 또는 SQL 에러
    @ExceptionHandler(value = SQLException.class)
    public ResponseEntity SQLException(SQLException e){
        body = new LinkedHashMap();


        body.put("code", ValidationCode.SQL_ERROR.getCode());
        body.put("message", ValidationCode.SQL_ERROR.getMessage());
        body.put("description",ValidationCode.SQL_ERROR.getDescription());

        return ResponseEntity.status(ValidationCode.SQL_ERROR.getCode()).body(body);
    }

    // 그 외 오류
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity Exception(Exception e){
        body = new LinkedHashMap();

        body.put("code", 999);
        body.put("message", "error");
        body.put("description","오류");

        return ResponseEntity.status(999).body(body);
    }

}
