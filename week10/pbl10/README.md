# Likelion PBL Week10

## 프로젝트 소개

멋쟁이사자처럼 10주차 개인 미니 프로젝트입니다. Member와 Assignment CRUD API에 전역 예외 처리를 적용하고, 제공된 프론트엔드 화면에서 백엔드 API 호출과 JSON 응답 흐름을 확인할 수 있도록 구성했습니다.

## 기술 스택

| 구분 | 기술 |
| --- | --- |
| Language | Java 17 |
| Framework | Spring Boot 3.5.14 |
| Web | Spring Web |
| Persistence | Spring Data JPA |
| Database | MySQL |
| Build Tool | Gradle |
| Frontend | HTML, CSS, JavaScript fetch API |

## 실행 방법

1. MySQL에서 데이터베이스를 생성합니다.

```sql
CREATE DATABASE likelion_pbl;
```

2. `src/main/resources/application.yaml`의 DB 계정 정보를 본인 환경에 맞게 확인합니다.

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/likelion_pbl?serverTimezone=Asia/Seoul&characterEncoding=UTF-8
    username: root
    password: root
```

3. 프로젝트 루트에서 애플리케이션을 실행합니다.

```bash
./gradlew bootRun
```

Windows PowerShell에서는 아래 명령을 사용할 수 있습니다.

```powershell
.\gradlew.bat bootRun
```

4. 브라우저에서 접속합니다.

```text
http://localhost:8080
```

## 핵심 구현 포인트

### 전역 예외 처리

`@RestControllerAdvice`는 모든 `@RestController`에서 발생한 예외를 전역에서 가로채는 예외 처리기입니다. Controller마다 `try-catch`나 `null` 체크를 반복하지 않아도 되고, 에러 응답 형식을 한 곳에서 관리할 수 있습니다.

`@ExceptionHandler`는 특정 예외 타입을 처리할 메서드를 지정합니다. 예를 들어 `MemberNotFoundException`이 발생하면 `GlobalExceptionHandler`의 멤버 조회 실패 핸들러가 실행되고, 404 상태 코드와 `ErrorResponse`를 반환합니다.

커스텀 예외는 비즈니스 상황을 명확하게 표현하기 위해 사용했습니다.

| 예외 | 발생 상황 | HTTP 상태 |
| --- | --- | --- |
| `MemberNotFoundException` | 존재하지 않는 멤버 조회, 수정, 삭제, 과제 등록 시 멤버 없음 | 404 Not Found |
| `AssignmentNotFoundException` | 존재하지 않는 과제 조회, 수정, 삭제 | 404 Not Found |
| `DuplicateMemberException` | 이미 등록된 이름으로 멤버 등록 | 409 Conflict |

에러 응답 형식은 항상 동일합니다.

```json
{
  "status": 404,
  "message": "과제를 찾을 수 없습니다. id=9999"
}
```

### Service 리팩토링

리팩토링 전에는 Service가 실패 상황에서 `null` 또는 `false`를 반환하고, Controller가 이를 직접 검사했습니다. 리팩토링 후에는 Service가 문제가 생긴 지점에서 커스텀 예외를 던지고, Controller는 정상 응답만 반환합니다.

```java
private Member findMember(Long id) {
    return repository.findById(id)
            .orElseThrow(() -> new MemberNotFoundException("멤버를 찾을 수 없습니다. id=" + id));
}
```

이 방식은 계층별 책임을 분리하고, 같은 에러 처리 코드가 여러 Controller 메서드에 반복되는 문제를 줄입니다.

### 검색 API

Spring Data JPA의 쿼리 메서드 네이밍 규칙을 사용했습니다.

| Repository 메서드 | 역할 |
| --- | --- |
| `findByPart(String part)` | 파트가 일치하는 멤버 목록 조회 |
| `findByTitleContaining(String keyword)` | 제목에 키워드가 포함된 과제 검색 |

### 프론트엔드 통신 흐름

프론트엔드는 `src/main/resources/static`에 배치되어 Spring Boot가 정적 리소스로 제공합니다. `index.html`의 `httpFetch()` 함수가 모든 API 호출을 감싸고, JavaScript `fetch()`로 요청을 보냅니다.

요청 흐름은 다음과 같습니다.

```text
버튼 클릭 -> JS 이벤트 함수 실행 -> MemberAPI/AssignmentAPI 호출 -> httpFetch() -> 백엔드 API -> JSON 응답 -> 화면 렌더링
```

HTTP 에러 응답이 오면 `httpFetch()`가 응답 JSON의 `message` 값을 읽어 토스트 알림으로 표시하고, 하단 HTTP 통신 로그 패널에 요청/응답 정보를 기록합니다.

## API 목록

### Member API

| HTTP Method | URI | 설명 | Request Body |
| --- | --- | --- | --- |
| GET | `/members` | 전체 멤버 조회 | 없음 |
| GET | `/members?part={part}` | 파트별 멤버 필터링 | 없음 |
| GET | `/members/{id}` | 멤버 단건 조회 | 없음 |
| POST | `/members/lions` | 아기사자 등록 | `name`, `major`, `generation`, `part`, `studentId` |
| POST | `/members/staffs` | 운영진 등록 | `name`, `major`, `generation`, `part`, `position` |
| PUT | `/members/lions/{id}` | 아기사자 정보 수정 | `major`, `generation`, `part`, `studentId` |
| PUT | `/members/staffs/{id}` | 운영진 정보 수정 | `major`, `generation`, `part`, `position` |
| DELETE | `/members/{id}` | 멤버 삭제 | 없음 |

### Assignment API

| HTTP Method | URI | 설명 | Request Body |
| --- | --- | --- | --- |
| POST | `/members/{memberId}/assignments` | 특정 멤버에게 과제 등록 | `title`, `description` |
| GET | `/assignments` | 전체 과제 조회 | 없음 |
| GET | `/members/{memberId}/assignments` | 멤버별 과제 조회 | 없음 |
| GET | `/assignments/{id}` | 과제 단건 조회 | 없음 |
| GET | `/assignments/search?keyword={keyword}` | 과제 제목 검색 | 없음 |
| PUT | `/assignments/{id}` | 과제 수정 | `title`, `description` |
| DELETE | `/assignments/{id}` | 과제 삭제 | 없음 |

## HTTP 메서드와 CRUD 대응

| HTTP Method | CRUD | 사용 예시 |
| --- | --- | --- |
| POST | Create | 멤버 등록, 과제 등록 |
| GET | Read | 멤버 조회, 과제 조회, 검색 |
| PUT | Update | 멤버 수정, 과제 수정 |
| DELETE | Delete | 멤버 삭제, 과제 삭제 |

## 프로젝트 구조

```text
src/main/java/com/likelion/PBL/
├── MemberApplication.java
├── member/
│   ├── controller/
│   │   └── MemberController.java
│   ├── service/
│   │   └── MemberService.java
│   ├── repository/
│   │   └── MemberRepository.java
│   ├── domain/
│   │   ├── Member.java
│   │   └── RoleType.java
│   └── dto/
│       ├── LionCreateRequest.java
│       ├── LionUpdateRequest.java
│       ├── MemberResponse.java
│       ├── StaffCreateRequest.java
│       └── StaffUpdateRequest.java
├── assignment/
│   ├── controller/
│   │   └── AssignmentController.java
│   ├── service/
│   │   └── AssignmentService.java
│   ├── repository/
│   │   └── AssignmentRepository.java
│   ├── domain/
│   │   └── Assignment.java
│   └── dto/
│       ├── AssignmentCreateRequest.java
│       ├── AssignmentResponse.java
│       └── AssignmentUpdateRequest.java
└── global/
    ├── dto/
    │   └── ErrorResponse.java
    └── exception/
        ├── AssignmentNotFoundException.java
        ├── DuplicateMemberException.java
        ├── GlobalExceptionHandler.java
        └── MemberNotFoundException.java

src/main/resources/
├── application.yaml
└── static/
    ├── index.html
    ├── css/
    │   └── style.css
    └── js/
        ├── assignment.js
        └── member.js
```

## 패키지 역할

| 패키지 | 역할 |
| --- | --- |
| `member` | 멤버 도메인, DTO, Repository, Service, Controller |
| `assignment` | 과제 도메인, DTO, Repository, Service, Controller |
| `global.dto` | 공통 응답 DTO |
| `global.exception` | 커스텀 예외와 전역 예외 처리기 |
| `static` | 브라우저에서 실행되는 프론트엔드 파일 |

## 브라우저 검증 체크리스트

| 검증 항목 | 기대 결과 |
| --- | --- |
| 멤버 등록 | POST `/members/lions` 또는 `/members/staffs`, 201 응답 |
| 멤버 목록 조회 | GET `/members`, 200 응답 |
| 파트 필터링 | GET `/members?part=백엔드`, 200 응답 |
| 멤버 수정 | PUT `/members/lions/{id}` 또는 `/members/staffs/{id}`, 200 응답 |
| 멤버 삭제 | DELETE `/members/{id}`, 204 응답 |
| 과제 전체 조회 | GET `/assignments`, 200 응답 |
| 과제 제목 검색 | GET `/assignments/search?keyword=...`, 200 응답 |
| 없는 과제 조회 | GET `/assignments/9999`, 404 응답과 에러 토스트 |
| 중복 이름 등록 | POST `/members/lions`, 409 응답과 에러 토스트 |
| 서버 중지 상태 호출 | 네트워크 에러 토스트 |
