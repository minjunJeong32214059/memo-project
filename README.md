# 🗒️ 메모정(Memo-"Jeong")

Java Spring Boot와 MySQL을 활용한 개인용 미니 메모 애플리케이션입니다.  
AWS EC2와 Docker를 사용하여 클라우드 환경에 성공적으로 배포되었습니다.

---

## 🚀 프로젝트 개요
- **개발자**: 정민준 (Jeong Min-jun)
- **개발 기간**: 2026.02
- **운영 주소**: [http://44.215.107.77:8080](http://44.215.107.77:8080)

---

## 🛠️ 기술 스택
- **Backend**: Java 23, Spring Boot, JPA
- **Database**: MySQL 8.0
- **Infrastructure**: AWS EC2, Docker, Docker Compose

---

## 📦 설치 및 실행 (Installation & Run)

아래 명령어를 통째로 복사하여 터미널에 붙여넣으면 바로 실행됩니다.

```bash
# 1. 저장소 클론 및 폴더 이동
git clone [https://github.com/minjunJeong32214059/memo-project.git](https://github.com/minjunJeong32214059/memo-project.git)
cd memo-project

# 2. 빌드 및 도커 실행 (한 번에 복사 가능)
./gradlew build -x test
docker-compose up -d --build
```
---

## ☁️ 배포 및 운영 전략

### 1. Docker 기반 배포 전략
- **Docker Network**: 애플리케이션과 DB 컨테이너 간의 독립적인 보안 통신망을 구축하였습니다.
- **Docker Volume**: 볼륨 마운트를 설정하여 컨테이너 재시작 시에도 메모 데이터 및 업로드 파일을 영구적으로 보존합니다.

### 2. 서버 최적화 및 비용 관리
- **Storage**: AWS EBS 볼륨을 20GB로 확장하여 배포 및 운영을 위한 안정적인 공간을 확보하였습니다.
- **Optimization**: `.dockerignore` 파일을 설정하여 빌드 시 불필요한 파일을 제외하고 이미지 빌드 속도를 최적화하였습니다.
- **Monitoring**: AWS Budgets를 통해 실시간 지출을 감시하고, 예산($0.01) 초과 시 즉시 알림을 받는 체계를 구축하였습니다.
