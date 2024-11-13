package com.springboot.ref_code.exceptionhandler.exception;

public enum ValidationCode {
    SUCCESS(200,"Success","성공"),
    REQUEST_BODY_MISSING_ERROR(400,"[POST] Required request body is missing :", "[POST] 필수 파라미터 누락"),
    REQUEST_BODY_ERROR(400,"[POST] Check Request body parameter :","[POST] 바디 파라미터 확인"),
    // GET METHOD 에서 DTO 사용을 위해 생성
    REQUEST_PARAM_ERROR(400,"[GET] Required Parameter is missing Or Check Request Parameter :","[GET] 필수 파라미터 누락 및 파라미터 확인"),
    NOT_FOUND_URL_ERROR(404,"Check Request URL","요청 주소 확인"),
    NOT_ALLOW_METHOD_ERROR(405,"Check Request METHOD :", "요청 메소드 확인"),
    SQL_ERROR(500,"Check Request Parameter Or SQL Error","요청 파라미터 확인 또는 SQL 에러");


    private final int code;
    private final String message;
    private final String description;

    ValidationCode(int code, String message, String description){
        this.code = code;
        this.message = message;
        this.description = description;
    }

    public int getCode(){
        return this.code;
    }

    public String getMessage(){
        return this.message;
    }

    public String getDescription(){
        return this.description;
    }
}
