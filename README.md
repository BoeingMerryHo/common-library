# boeingmerryho-common-library

boeingmerryho의 공통 라이브러리입니다.

# Usage

### Gradle
```gradle
implementation 'io.github.boeingmerryho:common-library:{version}'
```
### Maven
```xml
<dependency>
    <groupId>io.github.boeingmerryho</groupId>
    <artifactId>common-library</artifactId>
    <version>{version}</version>
</dependency>
```

# Version
| Version | Date       | Description                                                                                           |
|---------|------------|-------------------------------------------------------------------------------------------------------|
| 1.1.2   | 2025-04-17 | `Interceptor`의 `userId` Key 확인 로직 삭제                                                               |
| 1.1.1   | 2025-04-16 | `Interceptor`의 `getRequestBody` 메서드 추가                                                              |
| 1.1.0   | 2025-04-16 | `Interceptor` 추가                                                                                      |
| 1.0.4   | 2025-04-09 | QueryDsl Qclass 오류 수정                                                                                 |
| 1.0.3   | 2025-04-09 | `ErrorResponse`의 `of` 메서드 접근 제한자 오류 수정                                                                |
| 1.0.2   | 2025-04-08 | `application.yml` 파일 제거 및 의존성 경량화                                                                     |
| 1.0.1   | 2025-04-08 | `BaseEntity`, `SuccessResponse`, `SuccessCode`, `BaseErrorCode`, `ErrorResponse`, `GlobalException` 추가 |
| 1.0.0   | 2025-04-08 | 라이브러리 생성                                                                                              |
