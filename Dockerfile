# 1. 실행 환경 설정 (Java 23)
FROM bellsoft/liberica-openjdk-alpine:23

# 2. 컨테이너 내부 작업 폴더
WORKDIR /app

# 3. 빌드된 jar 파일을 컨테이너 내부로 복사
# (./gradlew build 실행 후 생성되는 파일을 복사합니다)
COPY build/libs/*.jar app.jar

# 4. 이미지 저장용 볼륨 설정 (C드라이브 대신 사용할 곳)
VOLUME /memo_files

# 5. 앱 실행
ENTRYPOINT ["java", "-jar", "app.jar"]