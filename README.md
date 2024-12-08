## 실행 시켜보기

### summary
1. port 8080
2. required: Java 17

### command
서버 실행 `./gradlew :app:api:bootRun`

### Swagger
docs path `/swagger-ui/index.html`

### oauth api
1. 카카오 로그인창 접근
   + path: {host}/oauth2/authorization/kakao
   + 로그인 완료 후 `access-token`, `refresh-token` cookie, header로 함께 전달

<br><br>

---

### 모듈 구조

```dtd
- app
    - 📦 api : [ 모든 controller 존재 (web 관련 모든 기능 존재) ] (Runnable)
    - core :
        - 📦 user  : [ user 도메인에 관한 비즈니스 로직 존재 ]
        - 📦 art : [ art 도메인에 관한 비즈니스 로직 존재 ]
    - 📦 model : [ 비즈니스와 연관이 있는 dto, vo, enum만 존재 ]
    - shared : 
        - 📦 auth : [ 인증, 보안에 관한 기능 ]
        - 📦 db : [ db 설정 ]
        - 📦 jpa : [ jpa 설정 ]
        - 📦 utils : [ 광범위하게 사용되면서 최대한 단순한 기능만 넣기를 권고 ]
```

    
<br> <br>
