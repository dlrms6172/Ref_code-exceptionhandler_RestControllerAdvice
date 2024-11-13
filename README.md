# exceptionhandler(@RestControllerAdvice)

#### 1.project information
- build : maven(4.0.0) 
- framework : springboot(2.0.2.RELEASE)

#### 2.project structure
- com
  - springboot
    - ref_code
        - exceptionhandler
          - controller
              - Controller : rest api controller(GET,POST 필수 파라미터 체크)
          - exception
              - ExceptionController : 발생한 오류에 대해 전역적으로 예외처리하도록 하는 클래스
              - ValidationCode : 오류에 대한 응답값을 설정해놓은 클래스
#### 3.project description
해당 프로젝트는 rest api에서 예외처리를 전역적으로 처리할 수 있는 @RestControllerAdvice 어노테이션을 공부하고 간단하게 참고할 수 있도록 만들었다.


