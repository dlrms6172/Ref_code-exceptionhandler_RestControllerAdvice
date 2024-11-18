# Ref_code-exceptionhandler(@RestControllerAdvice)

#### 1.project information
- build : maven(4.0.0) 
- framework : springboot(2.0.2.RELEASE)

#### 2.project structure
- com
  - springboot
    - ref_code
      - controller
        - Controller : rest api controller(GET,POST 필수 파라미터 체크)
      - exception
        - ExceptionController : 발생한 오류에 대해 전역적으로 예외처리하도록 하는 클래스
        - ValidationCode : 오류에 대한 응답값을 설정해놓은 클래스
#### 3.project description
해당 프로젝트는 REST API에서 전역적인 예외 처리를 위한 @RestControllerAdvice 애너테이션을 학습하고, 이를 간단히 참고할 수 있도록 제작되었습니다.


