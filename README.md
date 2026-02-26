# 📝 메모정 (Memo-"Jeong")
> **"_일상을 기록하세요_ "**

## 🚀 주요 기능 (Key Features)

- **메모 관리 (CRUD)**: 이미지와 텍스트를 포함한 메모 작성, 목록 조회, 수정 및 삭제 기능 제공
- **이미지 업로드**: UUID를 활용한 파일명 중복 방지 및 이미지 첨부 로직 구현
- **사용자 인증 및 권한 (Security)**:
  - Spring Security 기반의 회원가입 및 맞춤형 로그인 페이지 구현
  - **작성자 본인 검증**: 컨트롤러 계층에서 현재 세션 유저와 DB 작성자 정보를 비교하여 타인의 메모 조작 방지
- **검색 및 정렬**: 제목 기반 검색 기능 및 최신순 정렬(OrderByDescending) 적용
- **실시간 이미지 로딩**: 서버 외부 경로 매핑(Resource Handler)을 통해 별도의 새로고침 없이 업로드된 사진 즉시 확인 가능

## 🛠 사용 기술 (Tech Stack)

- **Backend**: Java 23, Spring Boot 4.0.2, Spring Data JPA, Spring Security
- **Frontend**: Thymeleaf, Bootstrap 5, HTML/CSS (CSS 분리 및 UI 최적화)
- **Database**: MySQL
- **Build Tool**: Gradle

## 💡 기술적 성취 (Key Points & Troubleshooting)

- **외부 리소스 매핑(Resource Mapping)**: 프로젝트 내부(`static`)가 아닌 로컬 드라이브(`C:/memo_files/`)에 파일을 저장하고, `WebConfig`를 통해 가상 경로로 연결하여 이클립스 빌드 경로 업데이트 문제(새로고침 현상)를 해결함
- **서버 측 보안 검증**: 프론트엔드에서 버튼을 숨기는 처리에 그치지 않고, 백엔드 로직에서 현재 로그인한 유저의 권한을 재검증하여 보안성 강화
- **UI/UX 개선 및 코드 구조화**: 
  - Bootstrap 기반의 커스텀 CSS를 별도 파일로 분리하여 코드의 가독성과 유지보수성 향상
  - 브라우저 기본 파일 업로드 UI를 커스텀 버튼 디자인으로 변경하여 일관된 사용자 경험 제공

## 📸 실행 화면

- **메인 화면**: 저장된 메모 리스트 확인 및 검색, 이미지 포함 새 메모 작성
- **로그인 페이지**: Spring Security와 맞춤형 CSS가 적용된 관리자 로그인 UI
