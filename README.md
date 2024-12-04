## 실행 시켜보기

### description
1. port 8080
2. 준비물 Java 17

### command
`./gradlew :app:api:bootRun`


<br><br>

---
## API

### 구조

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

### oauth api
1. 카카오 로그인창 접근
   + path: {host}/oauth2/authorization/kakao
   + 로그인 완료 후 `access-token`, `refresh-token` cookie를 발급해줍니다.

    
<br> <br>


### 테스트용 api
1. /api/users